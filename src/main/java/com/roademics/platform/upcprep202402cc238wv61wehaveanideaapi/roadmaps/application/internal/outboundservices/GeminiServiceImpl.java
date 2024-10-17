package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.application.internal.outboundservices;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.aggregates.AIInteraction;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.entities.Edge;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.entities.Node;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.valueobjects.JsonStructure;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.shared.interfaces.rest.resources.GeminiInterface;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeminiServiceImpl {

    private final GeminiInterface geminiInterface;
    private static final Logger LOGGER = LoggerFactory.getLogger(GeminiServiceImpl.class);
    private final ObjectMapper objectMapper = new ObjectMapper();  // Reutilizamos el ObjectMapper

    @Autowired
    public GeminiServiceImpl(GeminiInterface geminiInterface) {
        this.geminiInterface = geminiInterface;
    }

    // Obtener la interacción AI (nodos y aristas) desde Gemini
    public AIInteraction getAIInteractionCompletion(String userPrompt, String roadmapId) {
        String fullPrompt = buildPrompt(userPrompt);
        JsonStructure.GeminiRequest request = new JsonStructure.GeminiRequest(
                List.of(new JsonStructure.Content(List.of(new JsonStructure.TextPart(fullPrompt))))
        );

        // Llamar a Gemini API para obtener la respuesta
        JsonStructure.GeminiResponse response = geminiInterface.getCompletion(request);

        // Extraer nodos y aristas desde la respuesta
        List<Node> nodes = extractNodesFromGeminiResponse(response);
        List<Edge> edges = extractEdgesFromGeminiResponse(response);

        // Devolver un objeto AIInteraction que contiene los nodos y aristas
        return new AIInteraction(roadmapId, nodes, edges);
    }

    // Construir el prompt con instrucciones detalladas para la API
    private String buildPrompt(String userPrompt) {
        return userPrompt + "\n" +
                "A continuación, necesito que generes un roadmap de aprendizaje. " +
                "La salida debe estar estrictamente en formato JSON y debe contener dos estructuras principales: " +
                "`nodes` (los pasos o temas a cubrir) y `edges` (las relaciones entre los temas). La salida debe incluir lo siguiente: " +
                "1. nodes: Un arreglo de nodos, donde cada nodo tiene las siguientes propiedades: " +
                "- `nodeId`: Un identificador único para cada nodo, como un número o un string. " +
                "- `title`: El título que describe el tema principal del nodo (Ejemplo: 'Introducción a Java'). " +
                "- `description`: Una breve descripción del nodo que explique de qué trata. " +
                "- `isStartNode`: Un valor booleano (`true` o `false`) que indica si es el nodo inicial del roadmap. " +
                "- `isEndNode`: Un valor booleano (`true` o `false`) que indica si es el nodo final del roadmap. " +
                "2. edges: Un arreglo de conexiones entre los nodos, donde cada conexión tiene las siguientes propiedades: " +
                "- `fromNodeId`: El `nodeId` del nodo de origen. " +
                "- `toNodeId`: El `nodeId` del nodo de destino. " +
                "- `label`: Una breve descripción de la relación entre los nodos. " +
                "- `description`: Explicación del motivo por el cual el nodo de origen está relacionado con el nodo de destino. " +
                "- `relationshipType`: El tipo de relación entre los nodos ('Prerequisite', 'Optional', 'etc').";
    }

    // Extraer nodos desde la respuesta de Gemini
    private List<Node> extractNodesFromGeminiResponse(JsonStructure.GeminiResponse response) {
        List<Node> nodes = new ArrayList<>();

        for (JsonStructure.GeminiResponse.Candidate candidate : response.candidates()) {
            try {
                String nodeText = cleanJsonText(candidate.content().parts().get(0).text());
                JsonNode jsonNode = objectMapper.readTree(nodeText);

                if (jsonNode.has("nodes")) {
                    for (JsonNode nodeElement : jsonNode.get("nodes")) {
                        Node node = objectMapper.treeToValue(nodeElement, Node.class);
                        nodes.add(node);
                    }
                }
            } catch (Exception e) {
                LOGGER.error("Error parsing nodes from Gemini response: {}", e.getMessage());
            }
        }
        return nodes;
    }

    // Extraer aristas (edges) desde la respuesta de Gemini
    private List<Edge> extractEdgesFromGeminiResponse(JsonStructure.GeminiResponse response) {
        List<Edge> edges = new ArrayList<>();

        for (JsonStructure.GeminiResponse.Candidate candidate : response.candidates()) {
            try {
                String edgeText = cleanJsonText(candidate.content().parts().get(0).text());
                JsonNode jsonNode = objectMapper.readTree(edgeText);

                if (jsonNode.has("edges")) {
                    for (JsonNode edgeElement : jsonNode.get("edges")) {
                        Edge edge = objectMapper.treeToValue(edgeElement, Edge.class);
                        edges.add(edge);
                    }
                }
            } catch (Exception e) {
                LOGGER.error("Error parsing edges from Gemini response: {}", e.getMessage());
            }
        }
        return edges;
    }

    // Limpiar el texto JSON de backticks y etiquetas de bloque de código
    private String cleanJsonText(String text) {
        return text.replaceAll("```", "").replaceAll("json", "").trim();
    }
}
