package io.github.jarryzhou.modelexplorer.modelrecorder

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration


@ConfigurationProperties(prefix = "model-explorer")
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
class ModelExplorerProperties {
    var scanPackages: List<String>? = listOf("io.github.jarryzhou.modelexplorer")
    var hideClasses: List<String>? = emptyList()
    var activeProfiles: List<String>? = emptyList()
    var enabled: Boolean? = true
    var persistHistory: Boolean?= true
}