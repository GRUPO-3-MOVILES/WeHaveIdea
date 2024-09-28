package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.aggregates;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.commands.CreateRoadmapCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.entities.Edge;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.entities.Node;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "roadmaps")
public class Roadmap extends AuditableAbstractAggregateRoot<Roadmap> {

    private String ownerId; // El perfil del usuario que creó el roadmap
    private String title;
    private boolean isAIRecommended; // Determina si el roadmap tiene recomendaciones AI
    private String description;
    private List<Node> nodes = new ArrayList<>(); // Nodos del roadmap
    private List<Edge> edges = new ArrayList<>(); // Relaciones entre nodos
    private boolean isCompleted; // Si el roadmap está completo o no

    // Constructor
    public Roadmap(String ownerId, String title, String description, List<Node> nodes, List<Edge> edges) {
        this.ownerId = ownerId;
        this.title = title;
        this.description = description;
        this.nodes = nodes != null ? nodes : new ArrayList<>();
        this.edges = edges != null ? edges : new ArrayList<>();
    }

    // Constructor desde comando de creación
    public Roadmap(CreateRoadmapCommand command) {
        this.ownerId = command.ownerId();
        this.title = command.title();
        this.description = command.description();
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    // Metodo para agregar nodos y aristas generadas por IA (AIInteraction)
    public void addAIInteraction(AIInteraction aiInteraction) {
        this.nodes.addAll(aiInteraction.getNodes()); // Agregar todos los nodos
        this.edges.addAll(aiInteraction.getEdges()); // Agregar todas las aristas
        this.isAIRecommended = true; // Marcar que el roadmap incluye IA
    }

    // Metodo para agregar un nodo individualmente
    public void addNode(Node node) {
        if (!this.nodes.contains(node)) {
            this.nodes.add(node);
        }
    }

    // Metodo para agregar una arista (edge) individualmente
    public void addEdge(Edge edge) {
        if (!this.edges.contains(edge)) {
            this.edges.add(edge);
        }
    }

    // Marcar roadmap como completado
    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }
}
