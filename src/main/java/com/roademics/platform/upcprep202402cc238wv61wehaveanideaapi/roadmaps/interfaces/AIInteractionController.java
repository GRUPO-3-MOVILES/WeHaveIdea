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

    // Enviar un prompt a la IA (Gemini)
    @PostMapping("/send-prompt")
    public ResponseEntity<AIInteraction> sendPromptToAI(@RequestParam String conversationId, @RequestBody String prompt) {
        AIInteraction interaction = aiInteractionService.sendPromptToAI(prompt, conversationId);
        return ResponseEntity.ok(interaction);
    }
}
