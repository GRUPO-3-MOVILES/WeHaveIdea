package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.application.internal.commandservices;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.valueobjects.AIInteraction;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.valueobjects.PromptResponse;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.services.AIInteractionService;

public class AIInteractionServiceImpl implements AIInteractionService {

    private final AIInteractionService aiInteractionService;

    public AIInteractionServiceImpl(AIInteractionService aiInteractionService) {
        this.aiInteractionService = aiInteractionService;
    }

    @Override
    public AIInteraction startAIInteraction(String profileId, String conversationId) {
        AIInteraction aiInteraction = new AIInteraction();
        return aiInteraction;
    }

    @Override
    public PromptResponse sendPromptToAI(String prompt, String conversationId) {
        PromptResponse promptResponse = new PromptResponse();
        return promptResponse;
    }

    @Override
    public AIInteraction endAIInteraction(String conversationId) {
        AIInteraction aiInteraction = new AIInteraction();
        return aiInteraction;
    }
}
