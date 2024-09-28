package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.application.internal.commandservices;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.application.internal.outboundservices.GeminiServiceImpl;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.aggregates.AIInteraction;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.infrastructure.persistence.sdmbd.repositories.AIInteractionRepository;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.services.AIInteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AIInteractionServiceImpl implements AIInteractionService {

    private final GeminiServiceImpl geminiService;
    private final AIInteractionRepository aiInteractionRepository;

    @Autowired
    public AIInteractionServiceImpl(GeminiServiceImpl geminiService, AIInteractionRepository aiInteractionRepository) {
        this.geminiService = geminiService;
        this.aiInteractionRepository = aiInteractionRepository;
    }

    @Override
    public AIInteraction startAIInteraction(String profileId, String conversationId) {
        AIInteraction aiInteraction = new AIInteraction(conversationId, null, null);
        aiInteractionRepository.saveAIInteraction(aiInteraction);
        return aiInteraction;
    }

    @Override
    public AIInteraction sendPromptToAI(String prompt, String conversationId) {
        AIInteraction aiInteraction = geminiService.getAIInteractionCompletion(prompt, conversationId);
        aiInteractionRepository.saveAIInteraction(aiInteraction);
        return aiInteraction;
    }

    @Override
    public AIInteraction endAIInteraction(String conversationId) {
        AIInteraction aiInteraction = aiInteractionRepository.findByConversationId(conversationId)
                .orElseThrow(() -> new IllegalArgumentException("Interacción no encontrada"));
        aiInteractionRepository.deleteByConversationId(conversationId); // Finaliza la interacción eliminando
        return aiInteraction;
    }
}
