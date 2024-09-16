package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.entities;

import lombok.Getter;
import lombok.Setter;

public class Node {

    @Getter
    @Setter
    private String nodeId;

    @Getter
    @Setter
    private String title; // Ejemplo: "Java", "Front-End", "Back-End"

    @Getter
    @Setter
    private String description; // Descripción del curso o nodo

    @Getter
    @Setter
    private boolean isStartNode;  // Determina si es el nodo inicial

    @Getter
    @Setter
    private boolean isEndNode;  // Determina si es el nodo final

    public Node(String nodeId, String title, String description, boolean isStartNode, boolean isEndNode) {
        this.nodeId = nodeId;
        this.title = title;
        this.description = description;
        this.isStartNode = isStartNode;
        this.isEndNode = isEndNode;
    }
}

