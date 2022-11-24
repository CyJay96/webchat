package co.bukable.chatservice.mapper;

import co.bukable.chatservice.domain.ChatNotification;
import co.bukable.chatservice.dto.ChatNotificationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChatNotificationMapper {

    ChatNotification dtoToDomain(final ChatNotificationDto chatNotificationDto);

    ChatNotificationDto domainToDto(final ChatNotification chatNotification);

}
