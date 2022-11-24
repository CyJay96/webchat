package co.bukable.chatservice.controller;

import co.bukable.chatservice.domain.ChatMessage;
import co.bukable.chatservice.dto.response.ChatMessageDtoResponse;
import co.bukable.chatservice.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat")
    public void sendMessage(@Payload final ChatMessage chatMessage) {
        chatMessageService.sendChatMessage(chatMessage);
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessageDtoResponse>> getChatMessages(
            @PathVariable final String senderId,
            @PathVariable final String recipientId
    ) {
        return null;
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<ChatMessageDtoResponse> getChatMessage(@PathVariable final String id) {
        return null;
    }

}
