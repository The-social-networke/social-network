package com.socialnetwork.project.service.impl;

import com.socialnetwork.project.entity.Chat;
import com.socialnetwork.project.entity.User;
import com.socialnetwork.project.repository.ChatRepository;
import com.socialnetwork.project.repository.UserRepository;
import com.socialnetwork.project.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    @Override
    public Chat create(Chat chat) {
        return chatRepository.save(chat);
    }

    @Override
    @Transactional(readOnly = true)
    public Chat readById(Long id) {
        return chatRepository.getById(id);
    }

    @Override
    public Chat update(Chat chat) {
        return chatRepository.save(chat);
    }

    @Override
    public Chat delete(Long id) {
        Chat chat = chatRepository.getById(id);
        chatRepository.delete(chat);
        return chat;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Chat> getAll() {
        return chatRepository.findAll();
    }
}
