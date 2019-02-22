package ru.mityushin.responder.util.exception;

/**
 * Thrown to indicate exception in {@code MessageSenderService}
 *
 * @author Dmitry Mityushin
 * @since 1.0
 */
public class MessageSenderException extends RuntimeException {
    public MessageSenderException(String message) {
        super(message);
    }

    public MessageSenderException(Throwable cause) {
        super(cause);
    }
}
