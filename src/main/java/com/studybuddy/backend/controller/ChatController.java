package com.studybuddy.backend.controller;

import com.studybuddy.backend.object.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @MessageMapping("/app/chat/sendMessage")
    @SendTo("/topic/group")
    public ChatMessage broadcastGroupMessage(@Payload ChatMessage message) {
        //Sending this message to all the subscribers
        return message;
    }

    @MessageMapping("/app/chat/newUser")
    @SendTo("/topic/group")
    public ChatMessage addUser(@Payload ChatMessage message,
                           SimpMessageHeaderAccessor headerAccessor) {
        // Add user in web socket session
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        return message;
    }
}
