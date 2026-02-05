package com.example.Quiz.models;

import lombok.*;

import java.net.SocketOption;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
    private String content;
    private String sender;
    private MessageType type;


    public enum MessageType{
        CHAT, LEAVE, JOIN
    }
}
