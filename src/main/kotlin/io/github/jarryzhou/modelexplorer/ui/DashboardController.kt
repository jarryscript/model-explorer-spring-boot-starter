package io.github.jarryzhou.modelexplorer.ui

import io.github.jarryzhou.modelexplorer.modelrecorder.ModelService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

private const val VIEW_MODEL_DASHBOARD = "dashboard"
private const val VIEW_MODEL_LEFT_SECTION = "left-section::left-section"
private const val ATTRIBUTE_MODELS = "models"

@Controller
@RequestMapping("/model-explorer")
class DashboardController(private val modelService: ModelService) {

    @GetMapping
    fun index(model: Model): String? {
        model.addAttribute(ATTRIBUTE_MODELS, modelService.loadAllModelInfo())
        return VIEW_MODEL_DASHBOARD
    }

    @GetMapping("/left-section")
    fun refreshLeftSection(model: Model): String? {
        return getLeftSection(false, model)
    }

    @GetMapping("/left-section/reload")
    fun reloadLeftSection(model: Model): String? {
        return getLeftSection(true, model)
    }

    private fun getLeftSection(reload: Boolean, model: Model): String {
        if (reload) {
            modelService.reload()
        }
        model.addAttribute(ATTRIBUTE_MODELS, modelService.loadAllModelInfo())
        return VIEW_MODEL_LEFT_SECTION
    }
}
