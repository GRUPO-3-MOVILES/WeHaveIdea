package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.services;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.aggregates.AIInteraction;

import java.util.Optional;


public interface AIInteractionService {
    AIInteraction sendPromptToAI(String prompt, String conversationId);
    Optional<AIInteraction> getAIInteractionById(String aiInteractionId);
}
