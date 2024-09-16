package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.valueobjects;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.entities.Edge;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.entities.Node;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "ai_recommendations")
public class AIRecommendation {

    private String recommendationId;
    private String roadmapId;
    private List<Node> nodes;
    private List<Edge> edges;
    private PromptResponse promptResponse;

    // Constructor
    public AIRecommendation(String recommendationId, String roadmapId, List<Node> nodes, List<Edge> edges, PromptResponse promptResponse) {
        this.recommendationId = recommendationId;
        this.roadmapId = roadmapId;
        this.nodes = nodes;
        this.edges = edges;
        this.promptResponse = promptResponse;
    }

    // Getters y Setters
    public String getRecommendationId() {
        return recommendationId;
    }

    public void setRecommendationId(String recommendationId) {
        this.recommendationId = recommendationId;
    }

    public String getRoadmapId() {
        return roadmapId;
    }

    public void setRoadmapId(String roadmapId) {
        this.roadmapId = roadmapId;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public PromptResponse getPromptResponse() {
        return promptResponse;
    }

    public void setPromptResponse(PromptResponse promptResponse) {
        this.promptResponse = promptResponse;
    }

    // Metodo para aplicar las recomendaciones de la IA
    public void applyAIRecommendations() {
        // Usamos el PromptResponse para modificar la descripción de los nodos y los valores de las aristas.
        String aiGeneratedDescription = promptResponse.getResponse();

        // Modificamos las descripciones de los nodos basados en la respuesta de la IA
        for (Node node : nodes) {
            node.setDescription(aiGeneratedDescription);
        }

        // Modificamos las aristas con la misma respuesta de IA, esto puede variar según necesidades.
        for (Edge edge : edges) {
            edge.setLabel(aiGeneratedDescription);
            edge.setDescription(aiGeneratedDescription);
        }
    }
}


