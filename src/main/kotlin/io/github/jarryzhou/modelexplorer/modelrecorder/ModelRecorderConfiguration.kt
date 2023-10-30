package io.github.jarryzhou.modelexplorer.modelrecorder

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration


@ConfigurationProperties(prefix = "model-explorer")
@Configuration
class ModelRecorderConfiguration {
    var scanPackages: List<String>? = emptyList()
    var hideClasses: List<String>? = emptyList()
    var activeProfiles: List<String>? = emptyList()
    var enable: Boolean? = true
}