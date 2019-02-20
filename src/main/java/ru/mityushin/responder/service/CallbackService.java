package ru.mityushin.responder.service;

import ru.mityushin.responder.dto.CallbackDto;

public interface CallbackService {
    String handleCallback(CallbackDto callbackDto);
}
