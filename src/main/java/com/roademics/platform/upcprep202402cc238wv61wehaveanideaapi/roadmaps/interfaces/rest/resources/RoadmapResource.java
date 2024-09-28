package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.interfaces.rest.resources;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.entities.Edge;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.entities.Node;

import java.util.List;

public record RoadmapResource(String ownerId, String title, String description, List<Node> nodes, List<Edge> edges) {
}
