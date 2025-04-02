package com.liuhf.springai.api;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatApi {

    @Resource
    private ChatClient chatClient;
    
    @GetMapping("/ai/chat")
    public String chat(@RequestParam String message) {
        return chatClient.prompt().user(message).call().content();
    }


}
