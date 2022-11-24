package co.bukable.chatservice.controller;

import co.bukable.chatservice.domain.ChatMessage;
import co.bukable.chatservice.dto.response.ChatMessageDtoResponse;
import co.bukable.chatservice.mapper.ChatMessageMapper;
import co.bukable.chatservice.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatMessageService chatMessageService;
    private final ChatMessageMapper chatMessageMapper;

    @MessageMapping("/chat")
    public void sendMessage(@Payload final ChatMessage chatMessage) {
        chatMessageService.sendChatMessage(chatMessage);
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessageDtoResponse>> getChatMessages(
            @PathVariable final String senderId,
            @PathVariable final String recipientId
    ) {
        final List<ChatMessageDtoResponse> chatMessageDtoResponses = chatMessageService
                .fetchChatMessages(senderId, recipientId)
                .stream()
                .map(chatMessageMapper::domainToDtoResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(chatMessageDtoResponses, HttpStatus.OK);
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<ChatMessageDtoResponse> getChatMessage(@PathVariable final String id) {
        final ChatMessageDtoResponse chatMessageDtoResponse = chatMessageMapper
                .domainToDtoResponse(chatMessageService.fetchById(id));
        return new ResponseEntity<>(chatMessageDtoResponse, HttpStatus.OK);
    }

    @GetMapping("/messages/{senderId}/{recipientId}/count")
    public ResponseEntity<Long> countNewChatMessages(
            @PathVariable final String senderId,
            @PathVariable final String recipientId
    ) {
        final Long quantityNewChatMessages = chatMessageService.countNewChatMessages(senderId, recipientId);
        return new ResponseEntity<>(quantityNewChatMessages, HttpStatus.OK);
    }

}
