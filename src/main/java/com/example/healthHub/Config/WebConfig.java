package com.example.healthHub.Config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("PUT", "DELETE", "POST");
//                .allowedHeaders("header1", "header2", "header3")
//                .exposedHeaders("header1", "header2");
//                .allowCredentials(true).maxAge(3600);

        // Add more mappings...
    }
}
