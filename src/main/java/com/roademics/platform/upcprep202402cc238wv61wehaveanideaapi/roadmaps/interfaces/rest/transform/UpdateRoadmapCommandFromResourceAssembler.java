package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.interfaces.rest.transform;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.commands.UpdateRoadmapCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.interfaces.rest.resources.UpdateRoadmapResource;

public class UpdateRoadmapCommandFromResourceAssembler {
    public static UpdateRoadmapCommand toCommandFromResource(UpdateRoadmapResource resource) {
        return new UpdateRoadmapCommand(resource.id(), resource.title(), resource.description());
    }
}
