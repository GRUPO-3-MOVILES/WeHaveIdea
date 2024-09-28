package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.application.internal.repositoriesimpl;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.aggregates.AIInteraction;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.infrastructure.persistence.sdmbd.repositories.AIInteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AIInteractionRepositoryImpl implements AIInteractionRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    // Save AIInteraction to the database
    public void saveAIInteraction(AIInteraction aiInteraction) {
        mongoTemplate.save(aiInteraction);
    }

    // Find AIInteraction by conversationId
        public Optional<AIInteraction> findByConversationId(String conversationId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("conversationId").is(conversationId));
        return Optional.ofNullable(mongoTemplate.findOne(query, AIInteraction.class));
    }

    // Delete AIInteraction by conversationId
        public void deleteByConversationId(String conversationId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("conversationId").is(conversationId));
        mongoTemplate.remove(query, AIInteraction.class);
    }

    // Check if AIInteraction exists by conversationId
    public boolean existsByConversationId(String conversationId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("conversationId").is(conversationId));
        return mongoTemplate.exists(query, AIInteraction.class);
    }
}
