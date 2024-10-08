package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.aggregates;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.entities.Edge;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.entities.Node;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "ai_interactions")
public class AIInteraction extends AuditableAbstractAggregateRoot<AIInteraction> {

    private String roadmapId;
    private List<Node> nodes;
    private List<Edge> edges;

    // Constructor
    public AIInteraction(String roadmapId, List<Node> nodes, List<Edge> edges) {
        this.roadmapId = roadmapId;
        this.nodes = nodes;
        this.edges = edges;
    }

}


