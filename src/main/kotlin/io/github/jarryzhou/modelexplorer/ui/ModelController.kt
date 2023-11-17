package io.github.jarryzhou.modelexplorer.ui

import io.github.jarryzhou.modelexplorer.modelrecorder.DashboardService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

private const val RESPONSE_200_MESSAGE = "ok"

@RestController
@RequestMapping("/model-explorer/model")
class ModelController(private val modelService: DashboardService) {

    @DeleteMapping("/{id}")
    fun dashboard(@PathVariable("id") id: Long): ResponseEntity<String> {
        return ok()
    }

    private fun ok() = ResponseEntity.ok(RESPONSE_200_MESSAGE)


}