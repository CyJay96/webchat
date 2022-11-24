package co.bukable.chatservice.repository;

import co.bukable.chatservice.domain.ChatMessage;
import co.bukable.chatservice.domain.MessageStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {

    Long countBySenderIdAndRecipientIdAndStatus(final String senderId, final String recipientId, MessageStatus status);

    List<ChatMessage> findByChatId(final String chatId);

}
