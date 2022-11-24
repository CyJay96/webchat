package co.bukable.chatservice.mapper;

import co.bukable.chatservice.domain.ChatRoom;
import co.bukable.chatservice.dto.ChatRoomDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChatRoomMapper {

    ChatRoom dtoToDomain(final ChatRoomDto chatRoomDto);

    ChatRoomDto domainToDto(final ChatRoom chatRoom);

}
