package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.aggregates.AIInteraction;

import java.util.List;

public class JsonParser {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public AIInteraction parseResponse(String responseContent, String roadmapId) throws JsonProcessingException {
        try {
            // 1. Desescapar la cadena
            String validJson = responseContent.replace("\\\"", "\"").replace("\\n", "\n");

            // 2. Separar nodos y aristas (ya que están en dos arreglos diferentes en tu cadena)
            String[] jsonParts = validJson.split("], \\[");
            String nodesJson = jsonParts[0] + "]";
            String edgesJson = "[" + jsonParts[1];

            // 3. Deserializar nodos y aristas
            List<Node> nodes = objectMapper.readValue(nodesJson, new TypeReference<>() {
            });
            List<Edge> edges = objectMapper.readValue(edgesJson, new TypeReference<>() {
            });

            // 4. Crear el objeto AIInteraction con los nodos y aristas
            return new AIInteraction(roadmapId, nodes, edges);
        } catch (JsonProcessingException e) {
            throw new JsonProcessingException("Error parsing JSON response: " + e.getMessage()) {};
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error while parsing response: " + e.getMessage());
        }
    }
}
