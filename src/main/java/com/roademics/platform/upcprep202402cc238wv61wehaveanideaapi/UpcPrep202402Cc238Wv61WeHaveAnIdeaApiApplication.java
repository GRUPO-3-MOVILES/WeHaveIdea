package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi")
@EnableMongoRepositories
@EnableMongoAuditing
@Configuration
public class UpcPrep202402Cc238Wv61WeHaveAnIdeaApiApplication{

    public static void main(String[] args) {
        SpringApplication.run(UpcPrep202402Cc238Wv61WeHaveAnIdeaApiApplication.class, args);
    }
}