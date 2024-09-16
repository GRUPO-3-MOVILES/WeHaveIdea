package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.valueobjects;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class PromptResponse {

    // Getters y Setters
    @Setter
    @Getter

    private UUID conversationId;
    @Setter
    @Getter

    private String response;
    @Setter
    @Getter
    private String userPrompt;

    @Getter
    private Boolean isFirstPrompt;

    // Constructor
    public PromptResponse() {
    }

    public PromptResponse(UUID _conversationId, String _response, String _userPrompt) {
        this.conversationId = _conversationId;
        this.response = _response;
        this.userPrompt = _userPrompt;
        this.isFirstPrompt = false;
    }

    public PromptResponse(UUID _conversationId, String _response, String _userPrompt, Boolean _isFirstPrompt) {
        this.conversationId = _conversationId;
        this.response = _response;
        this.userPrompt = _userPrompt;
        this.isFirstPrompt = _isFirstPrompt;
    }

    public PromptResponse(UUID _conversationId, String _response) {
        this.conversationId = _conversationId;
        this.response = _response;
    }

    // toString para facilitar depuración
    @Override
    public String toString() {
        return "PromptResponse{" +
                "conversationId=" + conversationId +
                ", response='" + response + '\'' +
                ", userPrompt='" + userPrompt + '\'' +
                '}';
    }
}
