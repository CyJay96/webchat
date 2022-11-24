package co.bukable.chatservice.mapper;

import co.bukable.chatservice.domain.ChatNotification;
import co.bukable.chatservice.dto.ChatNotificationDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ChatNotificationMapper.class)
public interface ChatNotificationListMapper {

    List<ChatNotification> dtoToDomain(final List<ChatNotificationDto> chatNotificationDtos);

    List<ChatNotificationDto> domainToDto(final List<ChatNotification> chatNotifications);

}
