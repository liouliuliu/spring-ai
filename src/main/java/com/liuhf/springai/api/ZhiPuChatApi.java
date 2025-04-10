package com.liuhf.springai.api;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController()
@CrossOrigin("*")
@RequestMapping("/zhiPu")
public class ZhiPuChatApi {

    @Resource(name = "zhiPuAiChatClient")
    private ChatClient chatClient;

    @GetMapping("/ai/chat")
    public ChatResponse chat(@RequestParam String message) {
        return chatClient.prompt()
                .advisors(advisor -> advisor.param(AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY,"111")
                        .param(AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY,100))
                .user(message).call().chatResponse();
    }

    @RequestMapping(value = "/generate_stream", method = RequestMethod.GET)
    public Flux<ChatResponse> generateStream(@RequestParam String model, @RequestParam String message) {
        return chatClient.prompt()
                .advisors(advisor -> advisor.param(AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY, "111")
                        .param(AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY, 100))
                .user(message)
                .stream().chatResponse();
    }
}
