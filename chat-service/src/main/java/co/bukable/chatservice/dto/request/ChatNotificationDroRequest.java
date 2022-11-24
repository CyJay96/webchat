package co.bukable.chatservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatNotificationDroRequest {

    private String id;

    private String senderId;

    private String senderName;

}
