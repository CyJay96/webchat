package co.bukable.chatservice.service.impl;

import co.bukable.chatservice.domain.ChatMessage;
import co.bukable.chatservice.domain.ChatNotification;
import co.bukable.chatservice.domain.ChatRoom;
import co.bukable.chatservice.domain.MessageStatus;
import co.bukable.chatservice.exception.ResourceNotFoundException;
import co.bukable.chatservice.repository.ChatMessageRepository;
import co.bukable.chatservice.service.ChatMessageService;
import co.bukable.chatservice.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

    private final static String MESSAGE_DESTINATION = "/queue/messages";

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MongoOperations mongoOperations;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;

    @Override
    public void sendChatMessage(final ChatMessage chatMessage) {
        final List<ChatRoom> chatRooms = chatRoomService.
                fetchChatRoomsBySenderIdAndRecipientId(chatMessage.getSenderId(), chatMessage.getRecipientId());

        int firstElementIndex = 0;
        chatMessage.setChatId(chatRooms.get(firstElementIndex).getChatId());

        final ChatMessage savedChatMessage = saveChatMessage(chatMessage);

        simpMessagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId(),
                MESSAGE_DESTINATION,
                new ChatNotification(
                        savedChatMessage.getId(),
                        savedChatMessage.getSenderId(),
                        savedChatMessage.getRecipientName()
                )
        );
    }

    @Override
    public ChatMessage saveChatMessage(final ChatMessage chatMessage) {
        chatMessage.setStatus(MessageStatus.RECEIVED);
        return chatMessageRepository.save(chatMessage);
    }

    @Override
    public List<ChatMessage> fetchChatMessages(final String senderId, final String recipientId) {
        List<ChatRoom> chatRooms = chatRoomService.fetchChatRoomsBySenderIdAndRecipientId(senderId, recipientId);

        List<ChatMessage> chatMessages;
        try {
            int firstElementIndex = 0;
            chatMessages = chatMessageRepository.findByChatId(chatRooms.get(firstElementIndex).getChatId());
        } catch (Exception e) {
            chatMessages = new ArrayList<>();
        }

        updateStatuses(senderId, recipientId, MessageStatus.DELIVERED);

        return chatMessages;
    }

    @Override
    public ChatMessage fetchById(final String id) {
        return chatMessageRepository
                .findById(id)
                .map(chatMessage -> {
                    chatMessage.setStatus(MessageStatus.DELIVERED);
                    return chatMessageRepository.save(chatMessage);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Chat Message with id: " + id + " not found"));
    }

    @Override
    public Long countNewChatMessages(final String senderId, final String recipientId) {
        return chatMessageRepository
                .countBySenderIdAndRecipientIdAndStatus(senderId, recipientId, MessageStatus.RECEIVED);
    }

    @Override
    public void updateStatuses(final String senderId, final String recipientId, final MessageStatus status) {
        final Query query = new Query(
                Criteria.where("senderId").in(senderId)
                        .and("recipientId").is(recipientId)
        );
        final Update update = Update.update("status", status);
        mongoOperations.updateMulti(query, update, ChatMessage.class);
    }

}
