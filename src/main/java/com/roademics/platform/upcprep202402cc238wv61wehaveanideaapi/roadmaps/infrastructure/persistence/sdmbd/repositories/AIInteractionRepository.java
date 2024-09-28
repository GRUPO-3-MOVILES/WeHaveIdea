package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.infrastructure.persistence.sdmbd.repositories;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.aggregates.AIInteraction;

import java.util.Optional;

public interface AIInteractionRepository {
    void saveAIInteraction(AIInteraction aiInteraction);
    Optional<AIInteraction> findByConversationId(String conversationId);
    void deleteByConversationId(String conversationId);
    boolean existsByConversationId(String conversationId);
}
