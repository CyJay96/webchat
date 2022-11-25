package co.bukable.chatservice.controller;

import co.bukable.chatservice.dto.request.ChatMessageDtoRequest;
import co.bukable.chatservice.dto.response.ChatMessageDtoResponse;
import co.bukable.chatservice.mapper.ChatMessageMapper;
import co.bukable.chatservice.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Validated
@RequiredArgsConstructor
public class ChatController {

    private final ChatMessageService chatMessageService;
    private final ChatMessageMapper chatMessageMapper;

    @MessageMapping("/chat")
    public void sendMessage(@Payload @Valid final ChatMessageDtoRequest chatMessageDtoRequest) {
        chatMessageService.sendChatMessage(chatMessageMapper.dtoRequestToDomain(chatMessageDtoRequest));
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessageDtoResponse>> getChatMessages(
            @PathVariable @NotBlank final String senderId,
            @PathVariable @NotBlank final String recipientId
    ) {
        final List<ChatMessageDtoResponse> chatMessageDtoResponses = chatMessageService
                .fetchChatMessages(senderId, recipientId)
                .stream()
                .map(chatMessageMapper::domainToDtoResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(chatMessageDtoResponses, HttpStatus.OK);
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<ChatMessageDtoResponse> getChatMessage(@PathVariable @NotBlank final String id) {
        final ChatMessageDtoResponse chatMessageDtoResponse = chatMessageMapper
                .domainToDtoResponse(chatMessageService.fetchById(id));
        return new ResponseEntity<>(chatMessageDtoResponse, HttpStatus.OK);
    }

    @GetMapping("/messages/{senderId}/{recipientId}/count")
    public ResponseEntity<Long> countNewChatMessages(
            @PathVariable @NotBlank final String senderId,
            @PathVariable @NotBlank final String recipientId
    ) {
        final Long quantityNewChatMessages = chatMessageService.countNewChatMessages(senderId, recipientId);
        return new ResponseEntity<>(quantityNewChatMessages, HttpStatus.OK);
    }

}
