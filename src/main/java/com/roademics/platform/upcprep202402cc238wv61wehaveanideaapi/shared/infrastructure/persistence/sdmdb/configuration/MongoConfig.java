package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.shared.infrastructure.persistence.sdmdb.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Value("${spring.data.mongodb.database}")
    private String databaseName;  // Inyectar el nombre de la base de datos desde application.properties

    private MongoClient mongoClient;

    @Bean
    public MongoClient mongoClient() {
        this.mongoClient = MongoClients.create(mongoUri);
        return this.mongoClient;
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        System.out.println("Using database: " + databaseName);  // Para verificar el valor inyectado
        return new MongoTemplate(mongoClient(), databaseName);
    }


    @PreDestroy
    public void closeMongoClient() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
