package com.eazybytes.springai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class StreamController {
    private final ChatClient chatClient;

    public StreamController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    // Call this in browser http://localhost:8080/api/stream?message=What%20is%20your%20name%20and%20which%20model%20are%20you%20using
    @GetMapping("/stream")
    public Flux<String> stream(@RequestParam("message") String message) {
        return chatClient.prompt().user(message).stream().content();
    }
}