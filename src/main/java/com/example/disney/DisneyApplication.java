package com.example.disney;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.example.disney.entity", "com.example.disney.repository", "com.example.disney.auth.entity", "com.example.disney.auth.repository"})
public class DisneyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DisneyApplication.class, args);
    }
    // todo Falta testear
}
