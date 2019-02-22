package ru.mityushin.responder.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import ru.mityushin.responder.config.VkApiConfigurationProperties;
import ru.mityushin.responder.dto.MessagesSendDto;
import ru.mityushin.responder.util.exception.MessageSenderException;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class VkUriCreator {
    private final VkApiConfigurationProperties vkApiProperties;
    private final ObjectMapper objectMapper;

    public URI createUri(MessagesSendDto dto) {
        try {
            MultiValueMap<String, String> map = objectMapper.convertValue(dto, LinkedMultiValueMap.class);
            return UriComponentsBuilder.fromHttpUrl("https://api.vk.com/method/messages.send")
                    .queryParam("access_token", vkApiProperties.getAccessToken())
                    .queryParam("v", vkApiProperties.getV())
                    .queryParams(map)
                    .build()
                    .toUri();
        } catch (ClassCastException e) {
            throw new MessageSenderException(e);
        }
    }
}
