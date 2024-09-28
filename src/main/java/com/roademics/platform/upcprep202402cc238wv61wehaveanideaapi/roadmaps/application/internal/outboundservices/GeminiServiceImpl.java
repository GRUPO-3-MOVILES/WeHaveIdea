package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.application.internal.outboundservices;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.aggregates.AIInteraction;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.entities.Edge;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.entities.Node;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.valueobjects.JsonStructure;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.shared.interfaces.rest.resources.GeminiInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeminiServiceImpl {

    private final GeminiInterface geminiInterface;

    @Autowired
    public GeminiServiceImpl(GeminiInterface geminiInterface) {
        this.geminiInterface = geminiInterface;
    }

    // Metodo principal para obtener la interacción AI (nodos y aristas) desde Gemini
    public AIInteraction getAIInteractionCompletion(String userPrompt, String roadmapId) {
        // Construir el prompt completo con las instrucciones detalladas
        String fullPrompt = buildPrompt(userPrompt);

        // Crear la solicitud a Gemini API con el prompt completo
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

    // Metodo para construir el prompt con instrucciones detalladas para la API
    private String buildPrompt(String userPrompt) {
        return userPrompt + "\n\n" +
                "Genera una lista de nodos y conexiones para un roadmap de aprendizaje en programación en Java. " +
                "La lista debe estar en formato JSON y debe incluir:\n" +
                "1. Un arreglo de nodos (`nodes`), donde cada nodo tenga las siguientes propiedades:\n" +
                "- `nodeId`: Identificador único del nodo.\n" +
                "- `title`: El título del nodo que describe un tema (Ej: 'Java Basics', 'OOP in Java').\n" +
                "- `description`: Breve descripción del nodo.\n" +
                "- `isStartNode`: Booleano que indique si es el nodo inicial del roadmap.\n" +
                "- `isEndNode`: Booleano que indique si es el nodo final del roadmap.\n" +
                "2. Un arreglo de conexiones (`edges`), donde cada conexión tenga las siguientes propiedades:\n" +
                "- `fromNodeId`: El `nodeId` del nodo de origen.\n" +
                "- `toNodeId`: El `nodeId` del nodo de destino.\n" +
                "- `label`: Relación entre los nodos (Ej: 'Prerequisite').\n" +
                "- `description`: Breve descripción de la relación entre los nodos.\n" +
                "- `relationshipType`: Tipo de relación (Ej: 'Prerequisite', 'Optional').\n" +
                "Por favor responde solo con el JSON.";
    }

    // Metodo para extraer nodos desde la respuesta de Gemini
    private List<Node> extractNodesFromGeminiResponse(JsonStructure.GeminiResponse response) {
        // Extraer nodos desde la respuesta de Gemini
        List<Node> nodes = new ArrayList<>();
        for (JsonStructure.GeminiResponse.Candidate candidate : response.candidates()) {
            String nodeText = candidate.content().parts().get(0).text(); // Obtener el texto del nodo
            Node node = new Node(generateNodePosition(candidate), nodeText, nodeText, false, false);
            nodes.add(node); // Agregar el nodo a la lista
        }
        return nodes;
    }

    // Metodo para extraer aristas (edges) desde la respuesta de Gemini
    private List<Edge> extractEdgesFromGeminiResponse(JsonStructure.GeminiResponse response) {
        List<Edge> edges = new ArrayList<>();

        // Aquí asumimos que los edges son relaciones entre nodos que podemos inferir de los "candidates"
        for (int i = 0; i < response.candidates().size() - 1; i++) {
            // Extraer nodos consecutivos para generar una relación
            Node fromNode = new Node(
                    generateNodePosition(response.candidates().get(i)),
                    response.candidates().get(i).content().parts().get(0).text(),
                    response.candidates().get(i).content().parts().get(0).text(),
                    false,
                    false
            );

            Node toNode = new Node(
                    generateNodePosition(response.candidates().get(i + 1)),
                    response.candidates().get(i + 1).content().parts().get(0).text(),
                    response.candidates().get(i + 1).content().parts().get(0).text(),
                    false,
                    false
            );

            // Crear una nueva arista que conecta estos dos nodos
            Edge edge = new Edge(
                    fromNode.getNodePosition(), // Nodo de origen
                    toNode.getNodePosition(),   // Nodo de destino
                    "Prerequisite", // Etiqueta para esta relación
                    "Se requiere conocimiento del nodo previo", // Descripción básica
                    "Prerequisite" // Tipo de relación
            );

            edges.add(edge); // Agregar la arista a la lista
        }

        return edges;
    }

    // Metodo para generar la posición del nodo (usamos el índice del candidato para esto)
    private String generateNodePosition(JsonStructure.GeminiResponse.Candidate candidate) {
        return String.valueOf(candidate.index()); // Genera un ID basado en el índice del candidato
    }
}
