package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.shared.interfaces.rest.resources;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.valueobjects.JsonStructure;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/v1beta/models")
public interface GeminiInterface {
    @PostExchange("gemini-1.5-flash-latest:generateContent")
    JsonStructure.GeminiResponse getCompletion(@RequestBody JsonStructure.GeminiRequest request);
}
