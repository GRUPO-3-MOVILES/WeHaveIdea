package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.aggregates;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.commands.CreateRoadmapCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.entities.Edge;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.entities.Node;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "roadmaps")
@AllArgsConstructor
@NoArgsConstructor
public class Roadmap extends AuditableAbstractAggregateRoot<Roadmap> {

    private String ownerId; // El perfil del usuario que creó el roadmap
    private String title; // Título del roadmap que el usuario puede asignar
    private String description;  // Descripción del roadmap que el usuario puede asignar
    private String aiInteractionId; // ID de la interacción de IA
    private List<Node> nodes = new ArrayList<>(); // Nodos del roadmap
    private List<Edge> edges = new ArrayList<>(); // Relaciones entre nodos
    private boolean isCompleted; // Si el roadmap está completo o no

    // Constructor desde comando de creación
    public Roadmap(CreateRoadmapCommand command) {
        this.ownerId = command.ownerId();
        this.title = command.title();
        this.description = command.description();
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.aiInteractionId = command.aiInteractionId();
    }

    private static final Logger logger = LoggerFactory.getLogger(Roadmap.class);

    public void addAIInteraction(AIInteraction aiInteraction) {
        addNodes(aiInteraction.getNodes()); // Add all nodes
        addEdges(aiInteraction.getEdges()); // Add all edges
    }

    public void addEdges(List<Edge> edges) {
        for (Edge edge : edges) {
            if (!this.edges.contains(edge)) {
                this.edges.add(edge);
                logger.info("Edge added: {}", edge);
            }
        }
    }

    public void addNodes(List<Node> nodes) {
        for (Node node : nodes) {
            if (!this.nodes.contains(node)) {
                this.nodes.add(node);
            }
        }
    }

    // Marcar roadmap como completado
    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }
}
