package ru.mityushin.responder.service;

/**
 * Simple message sender service which allow send messages
 *
 * @param <T> message data transfer object
 * @author Dmitry Mityushin
 * @since 1.0
 */
public interface MessageSenderService<T> {
    /**
     * Send message
     *
     * @param message data transfer object contains message
     */
    void send(T message);
}
