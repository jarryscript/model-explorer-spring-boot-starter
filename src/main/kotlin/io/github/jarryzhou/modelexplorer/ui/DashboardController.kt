package io.github.jarryzhou.modelexplorer.ui

import io.github.jarryzhou.modelexplorer.modelrecorder.DashboardService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

private const val VIEW_MODEL_DASHBOARD = "dashboard"
private const val ATTRIBUTE_MODELS = "models"

@Controller
@RequestMapping("/model-explorer")
class DashboardController(private val dashboardService: DashboardService) {

    @GetMapping
    fun index(model: Model): String? {
        dashboardService.reload()
        model.addAttribute(ATTRIBUTE_MODELS, dashboardService.loadAll())
        return VIEW_MODEL_DASHBOARD
    }


}