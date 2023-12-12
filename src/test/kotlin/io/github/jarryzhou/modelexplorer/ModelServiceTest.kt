package io.github.jarryzhou.modelexplorer

import io.github.jarryzhou.modelexplorer.modelrecorder.ModelExplorerProperties
import io.github.jarryzhou.modelexplorer.modelrecorder.ModelRepository
import io.github.jarryzhou.modelexplorer.modelrecorder.ModelService
import io.github.jarryzhou.modelexplorer.ui.Model
import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

private const val LAST_DIAGRAM = ""

@ExtendWith(MockKExtension::class)
class ModelServiceTest {
    companion object {
        val model1: Model = Model(1, "model1", "model1", "")
        val model2: Model = Model(2, "model2", "model2", "")
        val models: List<Model> = listOf(model1, model2)
    }

    @MockK
    private lateinit var modelRepository: ModelRepository

    @MockK
    private lateinit var modelExplorerProperties: ModelExplorerProperties

    @InjectMockKs
    private lateinit var modelService: ModelService

    @Test
    fun `reload should ensure history table exist`() {
        every { modelExplorerProperties.scanPackages } returns listOf("test")
        every { modelExplorerProperties.hideClasses } returns listOf("test")
        every { modelRepository.getLastDiagram() } returns LAST_DIAGRAM
        every { modelRepository.ensureModelHistoryTable() } just Runs
        every { modelRepository.doRecord(any()) } just Runs
        modelService.reload()
        verify { modelRepository.ensureModelHistoryTable() }
    }

    @Test
    fun `loadAllModelInfo should return all models in the repository`() {
        every { modelRepository.findAllModels() } returns models
        val allModels = modelService.loadAllModelInfo()
        Assertions.assertEquals(models, allModels)
    }

    @Test
    fun `deleteById should return deleted row count`() {
        every { modelRepository.deleteById(any()) } returns 1
        val deletedRowCount = modelService.deleteById(1)
        Assertions.assertEquals(1, deletedRowCount)
    }

    @Test
    fun `findById should return model with the given id`() {
        every { modelRepository.findById(1) } returns model1
        val model = modelService.loadModelById(1)
        Assertions.assertEquals(model1, model)
    }
}
