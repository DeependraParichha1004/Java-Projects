package com.example.Quiz.Controller;


import com.example.Quiz.models.ChatMessage;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/public/messages")
    public ChatMessage sendMessage(@Payload ChatMessage chatmessage){
        return chatmessage;
    }

    @MessageMapping("/chat.register")
    @SendTo("/public/messages")
    public ChatMessage registerUser(@Payload ChatMessage chatmessage, SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("username", chatmessage.getSender());
        return chatmessage;
    }


}