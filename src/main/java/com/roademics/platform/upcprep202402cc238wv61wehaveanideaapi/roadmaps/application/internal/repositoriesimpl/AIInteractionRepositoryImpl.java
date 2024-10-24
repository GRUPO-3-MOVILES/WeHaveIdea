package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.application.internal.repositoriesimpl;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.aggregates.AIInteraction;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.infrastructure.persistence.sdmdb.repositories.AIInteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AIInteractionRepositoryImpl implements AIInteractionRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public AIInteractionRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    // Save AIInteraction to the database
    public void saveAIInteraction(AIInteraction aiInteraction) {
        mongoTemplate.save(aiInteraction);
    }

    @Override
    public Optional<AIInteraction> findById(String aiInteractionId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(aiInteractionId));
        return Optional.ofNullable(mongoTemplate.findOne(query, AIInteraction.class));
    }
}
