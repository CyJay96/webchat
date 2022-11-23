package co.bukable.chatservice.repository;

import co.bukable.chatservice.domain.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
}
