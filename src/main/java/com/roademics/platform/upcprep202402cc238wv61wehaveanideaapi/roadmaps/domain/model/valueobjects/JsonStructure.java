package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.valueobjects;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

public class JsonStructure {
    public record GeminiRequest(List<Content> contents) {}
    public record Content(List<Part> parts) {}

    public sealed interface Part permits TextPart, InLineDataPart {}

    public record TextPart(String text) implements Part{}

    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public record InLineDataPart(String text, String role) implements Part{}

    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public record  InLineData(String mimeType, String data) {}

    public record GeminiResponse(List<Candidate> candidates,
                                 PromptFeedback promptFeedback) {
        public record Candidate(Content content,
                                String finishReason,
                                int index,
                                List<SafetyRating> safetyRatings) {
            public record Content(List<TextPart> parts, String role) {}
        }
    }

    public record SafetyRating(String category, String probability) {}
    public record PromptFeedback(List<SafetyRating> safetyRatings) {}
}
