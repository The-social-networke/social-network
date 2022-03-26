package com.socialnetwork.project.mapper;

import com.socialnetwork.project.dto.SentMessageDTO;
import com.socialnetwork.project.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MessageMapper {

    @Mapping(source = "chatId", target = "chat.id")
    //@Mapping(source = "userId", target = "user.id")
    Message toMessage(SentMessageDTO messageDTO);

    @Mapping(source = "chat.id", target = "chatId")
    //@Mapping(source = "user.id", target = "userId")
    SentMessageDTO toSentMessageDTO(Message message);
}
