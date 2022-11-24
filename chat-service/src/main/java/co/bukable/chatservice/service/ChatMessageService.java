package co.bukable.chatservice.service;

import co.bukable.chatservice.domain.ChatMessage;
import co.bukable.chatservice.domain.MessageStatus;

import java.util.List;

public interface ChatMessageService {

    void sendChatMessage(final ChatMessage chatMessage);

    ChatMessage saveChatMessage(final ChatMessage chatMessage);

    List<ChatMessage> fetchChatMessages(final String senderId, final String recipientId);

    ChatMessage fetchById(final String id);

    Long countNewChatMessages(final String senderId, final String recipientId);

    void updateStatuses(final String senderId, final String recipientId, MessageStatus status);

}
