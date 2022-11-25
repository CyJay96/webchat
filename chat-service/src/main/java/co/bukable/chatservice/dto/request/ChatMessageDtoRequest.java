package co.bukable.chatservice.dto.request;

import co.bukable.chatservice.domain.MessageStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDtoRequest {

    @Id
    private String id;

    @NotBlank(message = "Chat ID cannot be empty")
    @Length(max = 255, message = "Chat ID is too long")
    private String chatId;

    @NotBlank(message = "Sender ID cannot be empty")
    @Length(max = 255, message = "Sender ID is too long")
    private String senderId;

    @NotBlank(message = "Recipient ID cannot be empty")
    @Length(max = 255, message = "Recipient ID is too long")
    private String recipientId;

    @NotBlank(message = "Sender name cannot be empty")
    @Length(max = 255, message = "Sender name is too long")
    private String senderName;

    @NotBlank(message = "Recipient name cannot be empty")
    @Length(max = 255, message = "Recipient name is too long")
    private String recipientName;

    @NotBlank(message = "Content cannot be empty")
    @Length(max = 255, message = "Content is too long")
    private String content;

    @Length(max = 1024, message = "File url is too long")
    private String fileUrl;

    private MessageStatus status;

}
