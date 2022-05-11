package com.socialnetwork.project.mapper;

import com.socialnetwork.project.dto.MessageCreateDTO;
import com.socialnetwork.project.dto.MessageDTO;
import com.socialnetwork.project.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface MessageMapper {

    @Mappings({
            @Mapping(source = "userId", target = "user.id"),
            @Mapping(source = "chatId", target = "chat.id")
    })
    Message toEntity(MessageCreateDTO dto);


    @Mappings({
            @Mapping(source = "user.id", target = "userId"),
            @Mapping(source = "chat.id", target = "chatId")
    })
    MessageDTO toMessageDTO(Message message);
}
