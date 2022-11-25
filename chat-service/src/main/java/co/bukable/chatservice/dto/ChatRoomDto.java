package co.bukable.chatservice.dto;

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
public class ChatRoomDto {

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

}
