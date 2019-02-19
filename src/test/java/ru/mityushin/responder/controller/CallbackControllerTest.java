package ru.mityushin.responder.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ru.mityushin.responder.dto.CallbackDto;
import ru.mityushin.responder.service.CallbackService;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class CallbackControllerTest {
    @Autowired
    private CallbackController callbackController;

    @Configuration
    static class ContextConfiguration {
        @Bean
        public CallbackService callbackService() {
            CallbackService mock = Mockito.mock(CallbackService.class);
            Mockito.doThrow(UnsupportedOperationException.class).when(mock).handleCallback(null);
            return mock;
        }
        @Bean
        public CallbackController callbackController(CallbackService callbackService) {
            return new CallbackController(callbackService);
        }
    }

    @Test
    public void handleCallbackWithoutDto() {
        ResponseEntity<String> responseEntity = callbackController.handleCallback(null);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("ok", responseEntity.getBody());
    }

    @Test
    public void handleCallbackWithEmptyDto() {
        CallbackDto emptyDto = CallbackDto.builder().build();
        ResponseEntity<String> responseEntity = callbackController.handleCallback(emptyDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("ok", responseEntity.getBody());
    }
}