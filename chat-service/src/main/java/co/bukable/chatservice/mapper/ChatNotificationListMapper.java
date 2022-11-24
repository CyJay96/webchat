package co.bukable.chatservice.mapper;

import co.bukable.chatservice.domain.ChatNotification;
import co.bukable.chatservice.dto.request.ChatNotificationDroRequest;
import co.bukable.chatservice.dto.response.ChatNotificationDtoResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ChatNotificationMapper.class)
public interface ChatNotificationListMapper {

    List<ChatNotification> dtoRequestToDomain(final List<ChatNotificationDroRequest> chatNotificationDroRequests);

    List<ChatNotificationDtoResponse> domainToDtoResponse(final List<ChatNotification> chatNotifications);

}
