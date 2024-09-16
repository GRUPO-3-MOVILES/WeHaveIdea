package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.aggregates;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.commands.CreateRoadmapCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.entities.Edge;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.entities.Node;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "roadmaps")
public class Roadmap extends AuditableAbstractAggregateRoot<Roadmap> {

    @Getter
    @Setter
    private String ownerId; // El perfil del usuario que creó el roadmap

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private boolean isAIRecommended;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private List<Node> nodes;  // Nodos del roadmap

    @Getter
    @Setter
    private List<Edge> edges;  // Relaciones entre nodos

    public Roadmap(String ownerId, String title, String description, List<Node> nodes, List<Edge> edges) {
        this.ownerId = ownerId;
        this.title = title;
        this.description = description;
        this.nodes = nodes;
        this.edges = edges;
    }

    public Roadmap(CreateRoadmapCommand command) {
        this.ownerId = command.ownerId();
        this.title = command.title();
        this.description = command.description();
    }

    public void addNodesAndEdgesFromAIResponse(String aiResponse) {
        // Implementación para analizar la respuesta de AI y convertirla en nodos y edges.
        // Puedes implementar un parser que convierta la respuesta de AI en nodos y relaciones.
    }

    // Metodo para agregar nodos
    public List<Edge> getNodes() {
        return edges;
    }

    public void addNode(Node node) {
        this.nodes.add(node);
    }

    // Metodo para agregar edges
    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }
}

