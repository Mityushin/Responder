package ru.mityushin.responder.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ru.mityushin.responder.config.VkApiConfigurationProperties;
import ru.mityushin.responder.dto.CallbackDto;
import ru.mityushin.responder.dto.MessagesSendDto;
import ru.mityushin.responder.entity.MessageNewCallback;
import ru.mityushin.responder.repo.MessageNewCallbackRepository;

import java.security.InvalidParameterException;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class MessageNewCallbackServiceTest {
    private static final String SECRET = "secret123";
    @Autowired
    private MessageNewCallbackRepository repository;
    @Autowired
    private MessageSenderService messageSenderService;
    @Autowired
    private CallbackService callbackService;

    @Configuration
    static class ContextConfiguration {
        @Bean
        public VkApiConfigurationProperties properties() {
            VkApiConfigurationProperties properties = new VkApiConfigurationProperties();
            properties.setSecret(SECRET);
            return properties;
        }
        @Bean
        public MessageNewCallbackRepository repository() {
            MessageNewCallbackRepository mock = Mockito.mock(MessageNewCallbackRepository.class);
            when(mock.save(any(MessageNewCallback.class))).then(returnsFirstArg());
            return mock;
        }
        @Bean
        public MessageSenderService messageSenderService() {
            return Mockito.mock(MessageSenderService.class);
        }
        @Bean
        public CallbackService callbackService(VkApiConfigurationProperties properties,
                                               MessageNewCallbackRepository repository,
                                               MessageSenderService<MessagesSendDto> messageSenderService) {
            return new MessageNewCallbackService(properties, repository, messageSenderService);
        }
    }

    @Test(expected = NullPointerException.class)
    public void handleCallbackWithoutDto() {
        callbackService.handleCallback(null);
    }

    @Test(expected = InvalidParameterException.class)
    public void handleCallbackWithoutSecret() {
        CallbackDto empty = CallbackDto.builder().build();
        callbackService.handleCallback(empty);
    }

    @Test(expected = InvalidParameterException.class)
    public void handleCallbackWithInvalidSecret() {
        CallbackDto invalid = CallbackDto.builder()
                .secret("")
                .build();
        callbackService.handleCallback(invalid);
    }

    @Test(expected = NullPointerException.class)
    public void handleUnsupportedTypeCallback() {
        CallbackDto unsupported = CallbackDto.builder()
                .secret(SECRET)
                .type(null)
                .build();
        callbackService.handleCallback(unsupported);
    }

    @Test
    public void handleValidCallback() {
        Map<String, String> objectMap = new LinkedHashMap<>();
        objectMap.put("id", "0");
        objectMap.put("date", "1");
        objectMap.put("peer_id", "2");
        objectMap.put("from_id", "3");
        objectMap.put("text", "Some text.");
        CallbackDto validDto = CallbackDto.builder()
                .secret(SECRET)
                .type(CallbackDto.CallbackType.message_new)
                .object(objectMap)
                .build();
        callbackService.handleCallback(validDto);
        verify(repository, times(1)).save(any(MessageNewCallback.class));
        verify(messageSenderService, times(1)).send(any());
    }
}