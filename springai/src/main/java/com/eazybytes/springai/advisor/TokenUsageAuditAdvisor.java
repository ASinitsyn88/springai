package com.eazybytes.springai.advisor;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;

public class TokenUsageAuditAdvisor implements CallAdvisor {
    private static final Logger logger = LoggerFactory.getLogger(TokenUsageAuditAdvisor.class);

    // Post LLM response logic
    @Override
    public ChatClientResponse adviseCall(@NonNull ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        ChatClientResponse response = callAdvisorChain.nextCall(chatClientRequest);
        logger.info("Token usage details: {}", response.chatResponse().getMetadata().getUsage());
        return response;
    }

    @Override
    public String getName() { return this.getClass().getName(); }

    @Override
    public int getOrder() { return 1; }
}