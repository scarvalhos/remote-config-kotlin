package com.sam.remoteconfig.config

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.context.annotation.Configuration
import org.springframework.beans.factory.annotation.Value

@Configuration
@EnableWebMvc
class WebConfig: WebMvcConfigurer {
    @Value("\${cors.originPatterns}")
    private val corsOriginPatterns: String = ""
    override fun addCorsMappings(registry: CorsRegistry) {
        val allowedOrigins = corsOriginPatterns.split(",").toTypedArray()
        registry.addMapping("/**")
            .allowedMethods("*")
            .allowedOriginPatterns(*allowedOrigins)
            .allowCredentials(true)
    }
}