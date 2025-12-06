package com.example.pokemon.configuration;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class DatabaseConfig {

    @Value("${DATABASE_HOST:localhost}")
    private String databaseURL;
}