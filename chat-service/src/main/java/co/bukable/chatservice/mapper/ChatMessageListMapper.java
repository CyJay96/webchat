package co.bukable.chatservice.mapper;

import co.bukable.chatservice.domain.ChatMessage;
import co.bukable.chatservice.dto.request.ChatMessageDtoRequest;
import co.bukable.chatservice.dto.response.ChatMessageDtoResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ChatMessageMapper.class)
public interface ChatMessageListMapper {

    List<ChatMessage> dtoRequestToDomain(final List<ChatMessageDtoRequest> chatMessageDtoRequests);

    List<ChatMessageDtoResponse> domainToDtoResponse(final List<ChatMessage> chatMessages);

}
