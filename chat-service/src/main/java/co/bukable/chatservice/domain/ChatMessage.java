package co.bukable.chatservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;

@Document
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

    @Id
    private String id;

    private String chatId;

    private String senderId;

    private String recipientId;

    private String senderName;

    private String recipientName;

    private String content;

    private String fileUrl;

    private OffsetDateTime timestamp;

    private MessageStatus status;

}
