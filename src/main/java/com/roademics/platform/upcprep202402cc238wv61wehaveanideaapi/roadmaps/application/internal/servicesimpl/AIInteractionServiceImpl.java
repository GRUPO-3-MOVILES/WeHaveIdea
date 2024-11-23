package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.application.internal.servicesimpl;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.application.internal.outboundservices.GeminiServiceImpl;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.aggregates.AIInteraction;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.services.AIInteractionService;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.infrastructure.persistence.sdmdb.repositories.AIInteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AIInteractionServiceImpl implements AIInteractionService {

    MongoTemplate mongoTemplate;
    private final GeminiServiceImpl geminiService;
    private final AIInteractionRepository aiInteractionRepository;

    @Autowired
    public AIInteractionServiceImpl(GeminiServiceImpl geminiService, AIInteractionRepository aiInteractionRepository, MongoTemplate mongoTemplate) {
        this.geminiService = geminiService;
        this.mongoTemplate = mongoTemplate;
        this.aiInteractionRepository = aiInteractionRepository;
    }

    @Override
    public AIInteraction sendPromptToAI(String prompt) {
        AIInteraction aiInteraction = geminiService.getAIInteractionCompletion(prompt);
        aiInteractionRepository.saveAIInteraction(aiInteraction);
        return aiInteraction;
    }

    @Override
    public Optional<AIInteraction> getAIInteractionById(String aiInteractionId) {
        return aiInteractionRepository.findById(aiInteractionId);
    }
}
