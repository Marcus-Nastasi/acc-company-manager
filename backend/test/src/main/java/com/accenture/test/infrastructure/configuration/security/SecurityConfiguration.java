package com.accenture.test.infrastructure.configuration.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;

/**
 *
 * The security configuration.
 *
 * @author Marcus Nastasi
 * @version 1.0.1
 * @since 2024
 */
@Configuration
public class SecurityConfiguration extends DelegatingWebMvcConfiguration {

    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowCredentials(true)
            .allowedHeaders("*")
            .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE")
            .allowedOrigins("http://localhost:3000");
    }
}
