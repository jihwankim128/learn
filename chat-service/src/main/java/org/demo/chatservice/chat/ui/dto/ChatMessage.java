package org.demo.chatservice.chat.ui.dto;

public record ChatMessage(
        String sender,
        String message
) {
}
