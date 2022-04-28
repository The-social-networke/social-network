package com.socialnetwork.project.mapper;

import com.socialnetwork.project.dto.MessageDTO;
import com.socialnetwork.project.dto.SentMessageDTO;
import com.socialnetwork.project.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MessageMapper {

    Message toMessage(SentMessageDTO messageDTO);

    MessageDTO toMessageDTO(Message message);
}
