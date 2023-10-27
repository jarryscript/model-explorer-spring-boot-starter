package io.github.jarryzhou.modelexplorer.ui

import io.github.jarryzhou.modelexplorer.modelrecorder.ModelRecorder
import org.springframework.stereotype.Component

@Component
class DashboardService(private val modelRecorder: ModelRecorder) {

    fun loadModels():List<ModelDto>{
        modelRecorder.reload()
        return modelRecorder.loadAll()
    }
}