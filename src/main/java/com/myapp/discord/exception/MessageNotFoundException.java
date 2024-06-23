package com.myapp.discord.exception;

public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException(Long id) {
        super("Message with id " + id + " not found");
    }
}
