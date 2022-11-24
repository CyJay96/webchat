package co.bukable.chatservice.service;

import co.bukable.chatservice.domain.ChatRoom;

public interface ChatRoomService {

    ChatRoom fetchChatRoomsBySenderIdAndRecipientId(final String senderId, final String recipientId);

}
