package co.bukable.chatservice.controller;

import co.bukable.chatservice.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> getChatMessages(
            @PathVariable final String senderId,
            @PathVariable final String recipientId
    ) {
        return null;
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<ChatMessage> getChatMessage(@PathVariable String id) {
        return null;
    }

}
