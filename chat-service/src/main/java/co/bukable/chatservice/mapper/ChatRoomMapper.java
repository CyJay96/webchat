package co.bukable.chatservice.mapper;

import co.bukable.chatservice.domain.ChatRoom;
import co.bukable.chatservice.dto.request.ChatRoomDtoRequest;
import co.bukable.chatservice.dto.response.ChatRoomDtoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChatRoomMapper {

    ChatRoom dtoRequestToDomain(final ChatRoomDtoRequest chatRoomDtoRequest);

    ChatRoomDtoResponse domainToDtoResponse(final ChatRoom chatRoom);

}
