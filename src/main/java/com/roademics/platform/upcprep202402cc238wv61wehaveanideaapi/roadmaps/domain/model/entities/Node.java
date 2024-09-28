package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "nodes")
public class Node {

    private String nodePosition; // Ejemplo: "1", "2", "3"

    private String title; // Ejemplo: "Java", "Front-End", "Back-End"

    private String description; // Descripción del curso o nodo

    private boolean isStartNode;  // Determina si es el nodo inicial

    private boolean isEndNode;  // Determina si es el nodo final

    public Node(String nodeId, String title, String description, boolean isStartNode, boolean isEndNode) {
        this.nodePosition = nodeId;
        this.title = title;
        this.description = description;
        this.isStartNode = isStartNode;
        this.isEndNode = isEndNode;
    }
}

