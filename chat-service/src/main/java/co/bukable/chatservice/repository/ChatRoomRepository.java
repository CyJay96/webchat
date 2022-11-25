package co.bukable.chatservice.repository;

import co.bukable.chatservice.domain.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {

    List<ChatRoom> findBySenderIdAndRecipientId(final String senderId, final String recipientId);

}
