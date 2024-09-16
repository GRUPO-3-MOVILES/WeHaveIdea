package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.infrastructure.persistence.sdmbd.repositories;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.valueobjects.AIRecommendation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AIRecommendationRepository extends MongoRepository<AIRecommendation, String> {
    List<AIRecommendation> saveRecommendations(List<AIRecommendation> recommendations);
    List<AIRecommendation> findByRoadmapId(String roadmapId);
}
