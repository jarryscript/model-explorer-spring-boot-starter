package io.github.jarryzhou.modelexplorer.modelrecorder

import de.elnarion.util.plantuml.generator.classdiagram.PlantUMLClassDiagramGenerator
import de.elnarion.util.plantuml.generator.classdiagram.config.PlantUMLClassDiagramConfigBuilder
import io.github.jarryzhou.modelexplorer.ui.Model
import net.sourceforge.plantuml.FileFormat
import net.sourceforge.plantuml.FileFormatOption
import net.sourceforge.plantuml.SourceStringReader
import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.io.ByteArrayOutputStream
import java.sql.Timestamp
import java.text.SimpleDateFormat


@Component
class ModelService(
    private val modelRepository: ModelRepository, private val modelExplorerProperties: ModelExplorerProperties
) {

    private val logger = LoggerFactory.getLogger(ModelService::class.java)

    fun reload() {
        ensureModelHistoryTable()
        val currentDiagram = generateDiagram()
        if (shouldCreateNewRecord(currentDiagram)) {
            logger.info("Changes found in models, creating new diagram")
            doRecord(currentDiagram)
        } else {
            logger.info("No Changes in models.")
        }
    }


    private fun toSvg(plantUmlString: String): String {
        val reader = SourceStringReader(plantUmlString)
        ByteArrayOutputStream().use { output ->
            reader.outputImage(output, FileFormatOption(FileFormat.SVG))
            return output.toString(Charsets.UTF_8)
        }
    }


    private fun generateDiagram(): String {
        return PlantUMLClassDiagramGenerator(
            PlantUMLClassDiagramConfigBuilder(modelExplorerProperties.scanPackages).withUseSmetana(true)
                .withHideClasses(
                    modelExplorerProperties.hideClasses
                ).build()
        ).generateDiagramText()
    }


    private fun shouldCreateNewRecord(currentDiagram: String): Boolean {
        return !org.thymeleaf.util.StringUtils.equals(modelRepository.getLastDiagram(), currentDiagram)
    }


    private fun ensureModelHistoryTable() {
        if (!doesHistoryTableExist()) {
            logger.info("Model history table does not exist, trying to create.")
            jdbcTemplate.execute(HISTORY_TABLE_DDL)
        }
    }


}