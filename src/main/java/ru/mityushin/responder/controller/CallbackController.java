package ru.mityushin.responder.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mityushin.responder.dto.CallbackDto;
import ru.mityushin.responder.service.CallbackService;

@Controller
@RequestMapping(value = "/callback")
@RequiredArgsConstructor
public class CallbackController {
    private static final Logger LOG = LoggerFactory.getLogger(CallbackController.class);
    private final CallbackService callbackService;

    @PostMapping
    public ResponseEntity<String> handleCallback(CallbackDto callbackDto) {
        try {
            callbackService.handleCallback(callbackDto);
        } catch (RuntimeException e) {
            LOG.error(e.getMessage(), e);
        }
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
