package com.elias.chat.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

@Entity
public class ChatMessage {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    public ChatMessage() {
    }

    public ChatMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
