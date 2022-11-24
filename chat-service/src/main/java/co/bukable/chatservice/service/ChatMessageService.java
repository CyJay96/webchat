package co.bukable.chatservice.service;

import co.bukable.chatservice.domain.ChatMessage;

public interface ChatMessageService {

    void sendChatMessage(final ChatMessage chatMessage);

    ChatMessage saveChatMessage(final ChatMessage chatMessage);

}
