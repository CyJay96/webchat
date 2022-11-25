package co.bukable.chatservice.dto.response;

import co.bukable.chatservice.domain.MessageStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDtoResponse {

    @Id
    private String id;

    private String chatId;

    private String senderId;

    private String recipientId;

    private String senderName;

    private String recipientName;

    private String content;

    private String fileUrl;

    private String timestamp;

    private MessageStatus status;

}
