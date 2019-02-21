package ru.mityushin.responder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.mityushin.responder.dto.MessagesSendDto;
import ru.mityushin.responder.dto.MessagesSendResultDto;
import ru.mityushin.responder.util.exception.MessageSenderException;

import java.net.URI;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class VkMessageSenderService implements MessageSenderService<MessagesSendDto> {
    private final RestTemplate restTemplate;
    private final VkUriCreator vkUriCreator;

    @Override
    public void send(MessagesSendDto message) {
        message.setRandomId((long) message.hashCode());
        URI uri = vkUriCreator.createUri(message);
        ResponseEntity<MessagesSendResultDto> responseEntity = restTemplate.getForEntity(uri, MessagesSendResultDto.class);
        validateResponse(responseEntity);
    }

    private void validateResponse(ResponseEntity<MessagesSendResultDto> responseEntity) {
        MessagesSendResultDto.MessagesSendErrorResultDto error = Objects.requireNonNull(responseEntity.getBody()).getError();
        if (error != null && error.getErrorCode() != null) {
            throw new MessageSenderException(error.getErrorMsg());
        }
    }
}
