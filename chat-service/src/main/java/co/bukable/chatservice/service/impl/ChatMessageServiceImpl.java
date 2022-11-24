package co.bukable.chatservice.service.impl;

import co.bukable.chatservice.domain.ChatMessage;
import co.bukable.chatservice.domain.ChatNotification;
import co.bukable.chatservice.domain.ChatRoom;
import co.bukable.chatservice.domain.MessageStatus;
import co.bukable.chatservice.repository.ChatMessageRepository;
import co.bukable.chatservice.service.ChatMessageService;
import co.bukable.chatservice.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

    private final static String MESSAGE_DESTINATION = "/queue/messages";

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;

    @Override
    public void sendChatMessage(final ChatMessage chatMessage) {
        final ChatRoom chatRoom = chatRoomService.
                fetchChatRoomBySenderIdAndRecipientId(chatMessage.getSenderId(), chatMessage.getRecipientId());

        chatMessage.setChatId(chatMessage.getChatId());

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

}
