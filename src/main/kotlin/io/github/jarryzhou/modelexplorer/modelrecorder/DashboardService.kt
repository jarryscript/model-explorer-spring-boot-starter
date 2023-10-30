package io.github.jarryzhou.modelexplorer.modelrecorder

import io.github.jarryzhou.modelexplorer.ui.ModelDto
import org.springframework.stereotype.Component

@Component
class DashboardService(private val modelRecorder: ModelRecorder) {

    fun loadModels():List<ModelDto>{
        modelRecorder.reload()
        return modelRecorder.loadAll()
    }
}