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

@Service
@RequiredArgsConstructor
public class MessageNewCallbackService implements CallbackService {
    private final VkApiConfigurationProperties vkApiConfigurationProperties;
    private final MessageNewCallbackRepository messageNewCallbackRepository;
    private final MessageSenderService<MessagesSendDto> messageSenderService;

    @Override
    public void handleCallback(CallbackDto callbackDto) {
        validateSecret(callbackDto);
        if (CallbackDto.CallbackType.message_new.equals(callbackDto.getType())) {
            MessageNewCallback messageNewCallback = parseMessageNewCallback(callbackDto);
            handleMessageNew(messageNewCallback);
        } else {
            throw new UnsupportedOperationException("Service support only 'message_new' callback type");
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
                .userId(saved.getFromId())
                .message(saved.getText())
                .groupId(saved.getGroupId())
                .build();
        messageSenderService.send(dto);
    }

    private static MessageNewCallback parseMessageNewCallback(CallbackDto callbackDto) {
        Map<String, String> map = callbackDto.getObject();
        return MessageNewCallback.builder()
                .id(Long.parseLong(map.get("id")))
                .date(Long.parseLong(map.get("date")))
                .peerId(Long.parseLong(map.get("peer_id")))
                .fromId(Long.parseLong(map.get("from_id")))
                .text(map.get("text"))
                .groupId(callbackDto.getGroupId())
                .build();
    }
}
