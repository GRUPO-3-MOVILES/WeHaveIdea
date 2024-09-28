package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.interfaces;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.aggregates.AIInteraction;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.services.AIInteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai-interactions")
public class AIInteractionController {

    private final AIInteractionService aiInteractionService;

    @Autowired
    public AIInteractionController(AIInteractionService aiInteractionService) {
        this.aiInteractionService = aiInteractionService;
    }

    // Iniciar una interacción de IA
    @PostMapping("/start")
    public ResponseEntity<AIInteraction> startAIInteraction(@RequestParam String profileId, @RequestParam String conversationId) {
        AIInteraction interaction = aiInteractionService.startAIInteraction(profileId, conversationId);
        return ResponseEntity.ok(interaction);
    }

    // Enviar un prompt a la IA (Gemini)
    @PostMapping("/send-prompt")
    public ResponseEntity<AIInteraction> sendPromptToAI(@RequestParam String conversationId, @RequestBody String prompt) {
        AIInteraction interaction = aiInteractionService.sendPromptToAI(prompt, conversationId);
        return ResponseEntity.ok(interaction);
    }

    // Finalizar una interacción de IA
    @PostMapping("/end")
    public ResponseEntity<AIInteraction> endAIInteraction(@RequestParam String conversationId) {
        AIInteraction interaction = aiInteractionService.endAIInteraction(conversationId);
        return ResponseEntity.ok(interaction);
    }
}
