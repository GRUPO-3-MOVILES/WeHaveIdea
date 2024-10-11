package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Edge {

    private String fromNodeId;
    private String toNodeId;
    private String label;
    private String description;
    private String relationshipType; // Ejemplo: "Prerequisite", "Optional"

    @JsonCreator
    public Edge(@JsonProperty("fromNodeId") String fromNodeId,
                @JsonProperty("toNodeId") String toNodeId,
                @JsonProperty("label") String label,
                @JsonProperty("description") String description,
                @JsonProperty("relationshipType") String relationshipType) {
        this.fromNodeId = fromNodeId;
        this.toNodeId = toNodeId;
        this.label = label;
        this.description = description;
        this.relationshipType = relationshipType;
    }
}
