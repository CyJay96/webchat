package co.bukable.chatservice.mapper;

import co.bukable.chatservice.domain.ChatMessage;
import co.bukable.chatservice.dto.request.ChatMessageDtoRequest;
import co.bukable.chatservice.dto.response.ChatMessageDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChatMessageMapper {

    @Mapping(target = "timestamp", expression = "java(java.time.OffsetDateTime.now())")
    ChatMessage dtoRequestToDomain(final ChatMessageDtoRequest chatMessageDtoRequest);

    @Mapping(target = "timestamp", expression = "java(chatMessage.getTimestamp().toString())")
    ChatMessageDtoResponse domainToDtoResponse(final ChatMessage chatMessage);

}
