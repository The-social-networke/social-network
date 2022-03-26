package com.socialnetwork.project.service.impl;

import com.socialnetwork.project.entity.Chat;
import com.socialnetwork.project.entity.Message;
import com.socialnetwork.project.repository.ChatRepository;
import com.socialnetwork.project.repository.MessageRepository;
import com.socialnetwork.project.service.MessageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message create(Message message) {
        return messageRepository.save(message);
    }

    @Transactional(readOnly = true)
    @Override
    public Message readById(Long id) {
        return messageRepository.getById(id);
    }

    @Override
    public Message update(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Message delete(Message message) {
        messageRepository.delete(message);
        return message;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Message> getAll() {
        return messageRepository.findAll();
    }
}
