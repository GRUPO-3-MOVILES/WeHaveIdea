package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.aggregates;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.entities.Edge;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.entities.Node;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "ai_interactions")
public class AIInteraction {

    private String roadmapId;
    private List<Node> nodes;
    private List<Edge> edges;

    // Constructor
    public AIInteraction(String roadmapId, List<Node> nodes, List<Edge> edges) {
        this.roadmapId = roadmapId;
        this.nodes = nodes;
        this.edges = edges;
    }

    // Metodo para fusionar nodos y aristas en un roadmap existente
    public void integrateWithRoadmap(Roadmap roadmap) {
        // Iterar sobre los nodos y agregar los que no existen en el roadmap
        for (Node node : this.nodes) {
            if (!roadmap.getNodes().contains(node)) {
                roadmap.addNode(node);
            }
        }

        // Iterar sobre las edges y agregar las que no existen en el roadmap
        for (Edge edge : this.edges) {
            if (!roadmap.getEdges().contains(edge)) {
                roadmap.addEdge(edge);
            }
        }
    }
}


