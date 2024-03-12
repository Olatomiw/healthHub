package com.example.healthHub.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {

//        TODO implement security
        http.formLogin((login)->login
                .defaultSuccessUrl("/welcome"));
//        Authorization
        http.authorizeHttpRequests((request)->request
                .requestMatchers("/welcome").permitAll());
        return http.build();
    }

}
