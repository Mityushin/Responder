package ru.mityushin.responder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class CallbackDto {
    private CallbackType type;
    private Map<String, String> object;
    @JsonProperty(value = "group_id")
    private Long groupId;
    private String secret;

    public enum CallbackType {
        message_new, confirmation
    }
}
