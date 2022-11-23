package co.bukable.chatservice.controller;

import co.bukable.chatservice.dto.response.ChatMessageDtoResponse;
import co.bukable.chatservice.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatMessageService chatMessageService;

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
