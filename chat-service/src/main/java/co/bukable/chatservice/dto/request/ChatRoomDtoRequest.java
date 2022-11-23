package co.bukable.chatservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomDtoRequest {

    private String id;

    private String chatId;

    private String senderId;

    private String recipientId;

}
