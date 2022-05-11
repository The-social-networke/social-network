package com.socialnetwork.project.mapper;

import com.socialnetwork.project.dto.ChatListDTO;
import com.socialnetwork.project.entity.Chat;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = {UserMapper.class, MessageMapper.class})
public interface ChatMapper {

}
