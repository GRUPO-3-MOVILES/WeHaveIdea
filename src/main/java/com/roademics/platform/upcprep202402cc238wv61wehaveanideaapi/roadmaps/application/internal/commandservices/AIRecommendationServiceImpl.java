package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.application.internal.commandservices;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.valueobjects.AIRecommendation;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.services.AIRecommendationService;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.infrastructure.persistence.sdmbd.repositories.AIRecommendationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AIRecommendationServiceImpl implements AIRecommendationService {

    private final AIRecommendationService aiRecommendationService;
    private final AIRecommendationRepository aiRecommendationRepository;

    public AIRecommendationServiceImpl(AIRecommendationService aiRecommendationService, AIRecommendationRepository aiRecommendationRepository) {
        this.aiRecommendationService = aiRecommendationService;
        this.aiRecommendationRepository = aiRecommendationRepository;
    }


    @Override
    public List<AIRecommendation> generateRecommendations(String userId, String roadmapId) {
        return List.of();
    }

    @Override
    public void applyAIRecommendations(String roadmapId, List<AIRecommendation> recommendations) {

    }
}
