package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.valueobjects;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "ai_interactions")
public class AIInteraction {

    private UUID interactionId;
    private String roadmapId;
    private String userPrompt;
    private String aiResponse;

    public AIInteraction() {
    }

    // Constructor
    public AIInteraction(UUID interactionId, String roadmapId, String userPrompt, String aiResponse) {
        this.interactionId = interactionId;
        this.roadmapId = roadmapId;
        this.userPrompt = userPrompt;
        this.aiResponse = aiResponse;
    }

    // Getters y Setters
    public UUID getInteractionId() {
        return interactionId;
    }

    public void setInteractionId(UUID interactionId) {
        this.interactionId = interactionId;
    }

    public String getRoadmapId() {
        return roadmapId;
    }

    public void setRoadmapId(String roadmapId) {
        this.roadmapId = roadmapId;
    }

    public String getUserPrompt() {
        return userPrompt;
    }

    public void setUserPrompt(String userPrompt) {
        this.userPrompt = userPrompt;
    }

    public String getAiResponse() {
        return aiResponse;
    }

    public void setAiResponse(String aiResponse) {
        this.aiResponse = aiResponse;
    }
}

