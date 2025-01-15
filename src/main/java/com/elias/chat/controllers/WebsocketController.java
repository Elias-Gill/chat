package com.elias.chat.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import com.elias.chat.models.*;

@Controller
public class WebsocketController {

    @MessageMapping("/hello")
    @SendTo("/notifications/greetings")
    public ServerResponse greeting(ChatMessage message) throws Exception {
        System.out.println("New message recieved: " + message.getContent());

        String response = "Hello, " + HtmlUtils.htmlEscape(message.getContent()) + "!";
        System.out.println("Server Response: " + response);

        Thread.sleep(200); // simulated delay
        return new ServerResponse(response);
    }
}
