package com.example.ragemusica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //Indica que esta clase define configuración de Spring
public class WebConfig {

    @Bean // método que devuelve un objeto
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @SuppressWarnings("null")
            @Override //estás sobrescribiendo un método que viene de una interfaz 
            public void addCorsMappings(CorsRegistry registry)//API acepte peticiones desde otros dominios
             {
                registry.addMapping("/**")
                        .allowedOrigins("*")  // Cambia "*" por tu dominio si es necesario
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH");
            }
        };
    }
}