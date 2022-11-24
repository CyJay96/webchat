package co.bukable.chatservice.mapper;

import co.bukable.chatservice.domain.ChatRoom;
import co.bukable.chatservice.dto.request.ChatRoomDtoRequest;
import co.bukable.chatservice.dto.response.ChatRoomDtoResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ChatRoomMapper.class)
public interface ChatRoomListMapper {

    List<ChatRoom> dtoRequestToDomain(final List<ChatRoomDtoRequest> chatRoomDtoRequests);

    List<ChatRoomDtoResponse> domainToDtoResponse(final List<ChatRoom> chatRooms);

}
