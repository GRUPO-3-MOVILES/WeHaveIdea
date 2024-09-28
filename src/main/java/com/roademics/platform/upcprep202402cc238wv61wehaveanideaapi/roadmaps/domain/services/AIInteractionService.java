package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.services;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.aggregates.AIInteraction;


public interface AIInteractionService {

    AIInteraction startAIInteraction(String profileId, String conversationId);

    AIInteraction sendPromptToAI(String prompt, String conversationId);

    AIInteraction endAIInteraction(String conversationId);
}
