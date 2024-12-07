package org.example.authservice.config

import org.example.authservice.utils.Constants
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig(
    @Value("\${app.cors.allowedOrigins}")
    private val allowedOrigins: Array<String>
) : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins(*allowedOrigins)
            .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
            .maxAge(Constants.MAX_AGE_SECS)
    }
}