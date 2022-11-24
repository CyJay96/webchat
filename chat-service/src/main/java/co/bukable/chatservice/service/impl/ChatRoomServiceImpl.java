package co.bukable.chatservice.service.impl;

import co.bukable.chatservice.domain.ChatRoom;
import co.bukable.chatservice.repository.ChatRoomRepository;
import co.bukable.chatservice.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    @Override
    public ChatRoom fetchChatRoomBySenderIdAndRecipientId(final String senderId, final String recipientId) {
        final Optional<ChatRoom> chatRoomOptional = chatRoomRepository
                .findBySenderIdAndRecipientId(senderId, recipientId);

        if (chatRoomOptional.isPresent()) {
            return chatRoomOptional.get();
        }

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

        chatRoomRepository.save(senderRecipient);
        chatRoomRepository.save(recipientSender);

        return senderRecipient;
    }

}
