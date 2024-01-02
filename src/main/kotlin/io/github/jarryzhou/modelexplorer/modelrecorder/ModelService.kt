package io.github.jarryzhou.modelexplorer.modelrecorder

import de.elnarion.util.plantuml.generator.classdiagram.PlantUMLClassDiagramGenerator
import de.elnarion.util.plantuml.generator.classdiagram.config.PlantUMLClassDiagramConfigBuilder
import net.sourceforge.plantuml.FileFormat
import net.sourceforge.plantuml.FileFormatOption
import net.sourceforge.plantuml.SourceStringReader
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.ByteArrayOutputStream

@Component
class ModelService(
    private val modelExplorerProperties: ModelExplorerProperties
) {

    private val logger = LoggerFactory.getLogger(ModelService::class.java)

    fun generate() = toSvg(generateDiagram())

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

}
