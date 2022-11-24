package co.bukable.chatservice.mapper;

import co.bukable.chatservice.domain.ChatRoom;
import co.bukable.chatservice.dto.ChatRoomDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ChatRoomMapper.class)
public interface ChatRoomListMapper {

    List<ChatRoom> dtoToDomain(final List<ChatRoomDto> chatRoomDtos);

    List<ChatRoomDto> domainToDto(final List<ChatRoom> chatRooms);

}
