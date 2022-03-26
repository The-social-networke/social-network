package com.socialnetwork.project.service.impl;

import com.socialnetwork.project.entity.Chat;
import com.socialnetwork.project.entity.User;
import com.socialnetwork.project.repository.ChatRepository;
import com.socialnetwork.project.repository.UserRepository;
import com.socialnetwork.project.service.ChatService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    public ChatServiceImpl(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public Chat create(Chat chat) {
        return chatRepository.save(chat);
    }

    @Transactional(readOnly = true)
    @Override
    public Chat readById(Long id) {
        return chatRepository.getById(id);
    }

    @Override
    public Chat update(Chat chat) {
        return chatRepository.save(chat);
    }

    @Override
    public Chat delete(Chat chat) {
        chatRepository.delete(chat);
        return chat;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Chat> getAll() {
        return chatRepository.findAll();
    }
}
