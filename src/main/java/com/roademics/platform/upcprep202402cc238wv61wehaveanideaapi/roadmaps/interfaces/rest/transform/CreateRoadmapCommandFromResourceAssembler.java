package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.interfaces.rest.transform;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.commands.CreateRoadmapCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.interfaces.rest.resources.CreateRoadmapResource;

public class CreateRoadmapCommandFromResourceAssembler {
    public static CreateRoadmapCommand toCommandFromResource(CreateRoadmapResource resource) {
        return new CreateRoadmapCommand(resource.ownerId(), resource.title(), resource.description(), resource.aiInteractionId());
    }
}
