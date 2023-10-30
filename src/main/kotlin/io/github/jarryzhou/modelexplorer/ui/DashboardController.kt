package io.github.jarryzhou.modelexplorer.ui

import io.github.jarryzhou.modelexplorer.modelrecorder.DashboardService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

private const val VIEW_MODEL_DASHBOARD = "dashboard"
private const val ATTRIBUTE_MODELS = "models"

@Controller
class DashboardController(private val dashboardService: DashboardService) {

    @GetMapping
    fun index(model: Model): String? {
        model.addAttribute(ATTRIBUTE_MODELS, dashboardService.loadModels())
        return VIEW_MODEL_DASHBOARD
    }


}