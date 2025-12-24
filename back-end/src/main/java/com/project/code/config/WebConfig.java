package com.project.code.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow CORS for all endpoints
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8000", "http://127.0.0.1:8000","http://0.0.0.0:8000")
                .allowedMethods("GET", "POST", "PUT", "DELETE","OPTIONS")  // Specify allowed methods
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
