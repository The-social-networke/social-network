package com.socialnetwork.project.controller;

import com.socialnetwork.project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("chats")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageController {

    private final UserService userService;
    private final ChatController chatController;
}
