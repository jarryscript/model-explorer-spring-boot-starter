package io.github.jarryzhou.modelexplorer.ui

import io.github.jarryzhou.modelexplorer.modelrecorder.ModelService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

private const val RESPONSE_200_MESSAGE = "ok"

@RestController
@RequestMapping("/model-explorer/model")
class ModelController(private val modelService: ModelService) {

    @DeleteMapping("/{id}")
    fun dashboard(@PathVariable("id") id: Long): ResponseEntity<String> {
        modelService.deleteById(id)
        return ok()
    }

    @GetMapping("/{id}")
    fun getModelById(@PathVariable("id") id: Long): ResponseEntity<Model> =
        ResponseEntity.ok(modelService.loadModelById(id))

    private fun ok() = ResponseEntity.ok(RESPONSE_200_MESSAGE)
}
