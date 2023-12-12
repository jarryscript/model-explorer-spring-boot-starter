package io.github.jarryzhou.modelexplorer.ui

data class Model(
    val id: Long,
    val name: String,
    val info: String,
    var diagram: String
)
