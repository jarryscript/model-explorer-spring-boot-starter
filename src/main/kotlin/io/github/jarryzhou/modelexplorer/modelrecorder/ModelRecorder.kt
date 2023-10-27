package io.github.jarryzhou.modelexplorer.modelrecorder

import de.elnarion.util.plantuml.generator.classdiagram.PlantUMLClassDiagramGenerator
import de.elnarion.util.plantuml.generator.classdiagram.config.PlantUMLClassDiagramConfigBuilder
import io.github.jarryzhou.modelexplorer.ui.ModelDto
import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet
import java.sql.Timestamp

private const val HISTORY_TABLE_NAME = "model_history"
private const val SELECT_ALL_DIAGRAM_SQL = "SELECT * FROM $HISTORY_TABLE_NAME"
private const val HISTORY_TABLE_DDL =
    "CREATE TABLE IF NOT EXISTS $HISTORY_TABLE_NAME (\n    id SERIAL PRIMARY KEY,\n    create_time TIMESTAMP,\n    diagram TEXT\n);"

private const val INSERT_HISTORY_SQL = "INSERT INTO $HISTORY_TABLE_NAME (create_time, diagram) VALUES (?, ?)"

private const val CHECK_HISTORY_TABLE_SQL =
    "SELECT EXISTS (\n   SELECT 1\n   FROM   pg_tables\n   WHERE    tablename = '$HISTORY_TABLE_NAME'\n);"

private const val SELECT_LAST_DIAGRAM_SQL = "SELECT *\nFROM $HISTORY_TABLE_NAME\nORDER BY create_time DESC\nLIMIT 1"

private const val TABLE_FIELD_DIAGRAM = "diagram"

private const val CREATE_DATE_PARAMETER_INDEX = 1

private const val SVG_PARAMETER_INDEX = 2

@Component
class ModelRecorder(
    private val jdbcTemplate: JdbcTemplate, private val modelRecorderConfiguration: ModelRecorderConfiguration
) {

    private val logger = LoggerFactory.getLogger(ModelRecorder::class.java)

    fun reload() {
        ensureModelHistoryTable()
        val currentDiagram = generateDiagram()
        if (shouldCreateNewRecord(currentDiagram)) {
            logger.info("Changes found in models, creating new diagram")
            doRecord(currentDiagram)
        }
    }

    fun loadAll():List<ModelDto>{
        return jdbcTemplate.query(SELECT_ALL_DIAGRAM_SQL) { rs, _ ->
            ModelDto(
                name=rs.getDate("create_time").toString(),
                diagram = rs.getString("diagram"),
                info = ""
            )
        }
    }

    private fun doRecord(diagram: String) {
        jdbcTemplate.update(INSERT_HISTORY_SQL) { ps ->
            ps.setTimestamp(CREATE_DATE_PARAMETER_INDEX, Timestamp(System.currentTimeMillis()))
            ps.setString(SVG_PARAMETER_INDEX, diagram)
        }
    }


    private fun getLastDiagram(): MutableList<Any> {
        return jdbcTemplate.query(SELECT_LAST_DIAGRAM_SQL) { rs: ResultSet, _: Int ->
            rs.getString(TABLE_FIELD_DIAGRAM)
        }
    }

    private fun generateDiagram(): String {
        return PlantUMLClassDiagramGenerator(PlantUMLClassDiagramConfigBuilder(modelRecorderConfiguration.scanPackages).withHideClasses(modelRecorderConfiguration.hideClasses).build()).generateDiagramText()
    }


    private fun shouldCreateNewRecord(currentDiagram: String): Boolean {
        return !org.thymeleaf.util.StringUtils.equals(getLastDiagram(), currentDiagram)
    }


    private fun ensureModelHistoryTable() {
        if (!doesHistoryTableExist()) {
            logger.info("Model history table does not exist, trying to create.")
            jdbcTemplate.execute(HISTORY_TABLE_DDL)
        }
    }

    private fun doesHistoryTableExist(): Boolean =
        jdbcTemplate.queryForObject(CHECK_HISTORY_TABLE_SQL, Boolean::class.java) ?: false

}