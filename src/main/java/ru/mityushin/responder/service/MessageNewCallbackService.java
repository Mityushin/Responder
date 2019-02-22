package ru.mityushin.responder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mityushin.responder.config.VkApiConfigurationProperties;
import ru.mityushin.responder.dto.CallbackDto;
import ru.mityushin.responder.dto.MessagesSendDto;
import ru.mityushin.responder.entity.MessageNewCallback;
import ru.mityushin.responder.repo.MessageNewCallbackRepository;

import java.security.InvalidParameterException;
import java.util.Map;
import java.util.Objects;

/**
 * Callback service which support handle {@code confirmation} and {@code message_new} callbacks
 *
 * @author Dmitry Mityushin
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class MessageNewCallbackService implements CallbackService {
    private final VkApiConfigurationProperties vkApiConfigurationProperties;
    private final MessageNewCallbackRepository messageNewCallbackRepository;
    private final MessageSenderService<MessagesSendDto> messageSenderService;

    @Override
    public String handleCallback(CallbackDto callbackDto) {
        validateSecret(callbackDto);
        switch (Objects.requireNonNull(callbackDto.getType())) {
            case confirmation: {
                return vkApiConfigurationProperties.getConfirmation();
            }
            case message_new: {
                MessageNewCallback messageNewCallback = parseMessageNewCallback(callbackDto);
                handleMessageNew(messageNewCallback);
                return "ok";
            }
            default: {
                throw new UnsupportedOperationException("Service support only 'message_new' callback type");
            }
        }
    }

    private void validateSecret(CallbackDto callbackDto) {
        if (!vkApiConfigurationProperties.getSecret().equals(callbackDto.getSecret())) {
            throw new InvalidParameterException("Invalid secret");
        }
    }

    private void handleMessageNew(MessageNewCallback messageNewCallback) {
        MessageNewCallback saved = messageNewCallbackRepository.save(messageNewCallback);
        MessagesSendDto dto = MessagesSendDto.builder()
                .peerId(saved.getPeerId())
                .message(saved.getText())
                .groupId(saved.getGroupId())
                .build();
        messageSenderService.send(dto);
    }

    private static MessageNewCallback parseMessageNewCallback(CallbackDto callbackDto) {
        Map<String, Object> map = callbackDto.getObject();
        return MessageNewCallback.builder()
                .id(Long.parseLong(String.valueOf(map.get("id"))))
                .date(Long.parseLong(String.valueOf(map.get("date"))))
                .peerId(Long.parseLong(String.valueOf(map.get("peer_id"))))
                .fromId(Long.parseLong(String.valueOf(map.get("from_id"))))
                .text("Вы сказали: " + map.get("text"))
                .groupId(callbackDto.getGroupId())
                .build();
    }
}
