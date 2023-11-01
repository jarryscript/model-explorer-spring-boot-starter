package io.github.jarryzhou.modelexplorer

import io.github.jarryzhou.modelexplorer.modelrecorder.ModelExplorerProperties
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(ModelExplorerProperties::class)
@ComponentScan("io.github.jarryzhou.modelexplorer")
@ConditionalOnProperty("model-explorer.enabled", havingValue = "true")
class ModelExplorerAutoConfig