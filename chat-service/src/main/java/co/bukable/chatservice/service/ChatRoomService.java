package co.bukable.chatservice.service;

import co.bukable.chatservice.domain.ChatRoom;

import java.util.List;

public interface ChatRoomService {

    List<ChatRoom> fetchChatRoomsBySenderIdAndRecipientId(final String senderId, final String recipientId);

}
