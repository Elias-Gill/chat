package com.chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.chat.models.ChatMessage;
import com.chat.models.HelloMessage;

@Controller
public class ChatController {

    // Recibes a "HelloMessage" and returns an "ChatMessage" out
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public ChatMessage greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new ChatMessage("Hello, " + HtmlUtils.htmlEscape(message.getMsg()) + "!");
    }
}
