package com.socialnetwork.project.mapper;

import com.socialnetwork.project.dto.MessageDTO;
import com.socialnetwork.project.dto.SentMessageDTO;
import com.socialnetwork.project.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface MessageMapper {

    Message toMessage(SentMessageDTO messageDTO);


    @Mappings({
            @Mapping(source = "user.id", target = "userId"),
            @Mapping(source = "chat.id", target = "chatId")
    })
    MessageDTO toMessageDTO(Message message);
}
