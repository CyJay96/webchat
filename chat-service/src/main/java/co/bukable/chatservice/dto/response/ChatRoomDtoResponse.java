package co.bukable.chatservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomDtoResponse {

    private String id;

    private String chatId;

    private String senderId;

    private String recipientId;

}
