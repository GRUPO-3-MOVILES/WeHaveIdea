package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "nodes")
public class Node {

    private String nodeId; // Identificador del nodo

    private String title; // Ejemplo: "Java", "Front-End", "Back-End"

    private String description; // Descripción del curso o nodo

    private boolean isStartNode;  // Determina si es el nodo inicial

    private boolean isEndNode;  // Determina si es el nodo final

    // Constructor anotado con @JsonCreator
    @JsonCreator
    public Node(@JsonProperty("nodeId") String nodeId,
                @JsonProperty("title") String title,
                @JsonProperty("description") String description,
                @JsonProperty("isStartNode") boolean isStartNode,
                @JsonProperty("isEndNode") boolean isEndNode) {
        this.nodeId = nodeId;
        this.title = title;
        this.description = description;
        this.isStartNode = isStartNode;
        this.isEndNode = isEndNode;
    }
}
