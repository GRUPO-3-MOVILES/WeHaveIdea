package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.services;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.valueobjects.AIRecommendation;

import java.util.List;

public interface AIRecommendationService {

    List<AIRecommendation> generateRecommendations(String userId, String roadmapId);

    void applyAIRecommendations(String roadmapId, List<AIRecommendation> recommendations);
}

