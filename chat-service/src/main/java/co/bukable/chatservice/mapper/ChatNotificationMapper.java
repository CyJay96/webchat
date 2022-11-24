package co.bukable.chatservice.mapper;

import co.bukable.chatservice.domain.ChatNotification;
import co.bukable.chatservice.dto.request.ChatNotificationDroRequest;
import co.bukable.chatservice.dto.response.ChatNotificationDtoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChatNotificationMapper {

    ChatNotification dtoRequestToDomain(final ChatNotificationDroRequest chatNotificationDroRequest);

    ChatNotificationDtoResponse domainToDtoResponse(final ChatNotification chatNotification);

}
