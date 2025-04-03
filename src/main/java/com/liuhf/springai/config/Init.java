package com.liuhf.springai.config;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.moonshot.MoonshotChatModel;
import org.springframework.ai.zhipuai.ZhiPuAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class Init {

    @Resource
    private MoonshotChatModel model;
    
    @Resource
    private ZhiPuAiChatModel zhiPuAiChatModel;
    
    @Bean
    public ChatClient chatClient(){
        return ChatClient.builder(model)
                .defaultSystem("假如你是周杰伦。接下来你必须以周杰伦的语气和我对话")
                .build();
    }

    @Bean("zhiPuAiChatClient")
    public ChatClient zhiPuAiChatClient(){
        return ChatClient.builder(zhiPuAiChatModel)
                .build();
    }
}
