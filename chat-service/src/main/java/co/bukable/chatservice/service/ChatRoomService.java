package co.bukable.chatservice.service;

import co.bukable.chatservice.domain.ChatRoom;

public interface ChatRoomService {

    ChatRoom fetchChatRoomBySenderIdAndRecipientId(final String senderId, final String recipientId);

}
