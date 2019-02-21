package ru.mityushin.responder.service;

public interface MessageSenderService<T> {
    void send(T message);
}
