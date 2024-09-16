package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Edge {

    private String fromNodeId;

    private String toNodeId;

    private String label;

    private String description;

    private String relationshipType; // Ejemplo: "Prerequisite", "Optional"

    public Edge(String _fromNodeId, String _toNodeId, String _label, String _description, String _relationshipType) {
        this.fromNodeId = _fromNodeId;
        this.toNodeId = _toNodeId;
        this.label = _label;
        this.description = _description;
        this.relationshipType = _relationshipType;
    }
}
