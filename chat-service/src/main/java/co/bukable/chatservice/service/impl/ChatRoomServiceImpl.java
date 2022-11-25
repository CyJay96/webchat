package co.bukable.chatservice.service.impl;

import co.bukable.chatservice.domain.ChatRoom;
import co.bukable.chatservice.repository.ChatRoomRepository;
import co.bukable.chatservice.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    @Override
    public List<ChatRoom> fetchChatRoomsBySenderIdAndRecipientId(final String senderId, final String recipientId) {
        List<ChatRoom> chatRooms;

        try {
            chatRooms = chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId);
        } catch (Exception e) {
            final String chatRoomId = String.format("%s_%s", senderId, recipientId);

            final ChatRoom senderRecipient = ChatRoom.builder()
                    .chatId(chatRoomId)
                    .senderId(senderId)
                    .recipientId(recipientId)
                    .build();

            final ChatRoom recipientSender = ChatRoom.builder()
                    .chatId(chatRoomId)
                    .senderId(recipientId)
                    .recipientId(senderId)
                    .build();

            chatRooms = new ArrayList<>(List.of(senderRecipient, recipientSender));

            chatRoomRepository.save(senderRecipient);
            chatRoomRepository.save(recipientSender);
        }

        return chatRooms;
    }

}
