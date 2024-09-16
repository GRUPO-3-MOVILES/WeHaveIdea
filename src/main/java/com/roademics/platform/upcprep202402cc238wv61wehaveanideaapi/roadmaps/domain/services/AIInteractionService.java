package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.services;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.valueobjects.AIInteraction;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.valueobjects.PromptResponse;

public interface AIInteractionService {

    AIInteraction startAIInteraction(String profileId, String conversationId);

    PromptResponse sendPromptToAI(String prompt, String conversationId);

    AIInteraction endAIInteraction(String conversationId);
}
