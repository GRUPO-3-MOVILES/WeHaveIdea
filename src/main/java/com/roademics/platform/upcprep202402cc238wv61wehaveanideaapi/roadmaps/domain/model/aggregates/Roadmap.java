package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.aggregates;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.commands.CreateRoadmapCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.entities.Edge;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.entities.Node;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@Data
@Document(collection = "roadmaps")
public class Roadmap extends AuditableAbstractAggregateRoot<Roadmap> {

    private String ownerId; // El perfil del usuario que creó el roadmap

    private String title;

    private boolean isAIRecommended;

    private String description;

    private List<Node> nodes;  // Nodos del roadmap

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
    public void addNode(Node node) {
        this.nodes.add(node);
    }

    // Metodo para agregar edges
    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }
}

