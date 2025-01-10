package com.elias.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        System.out.println("New message recieved: " + message.getName());

        String response = "Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!";
        System.out.println("Server Response: " + response);

        Thread.sleep(200); // simulated delay
        return new Greeting(response);
    }
}
