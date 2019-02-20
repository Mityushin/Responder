package ru.mityushin.responder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CallbackDto {
    private CallbackType type;
    private Map<String, Object> object;
    @JsonProperty(value = "group_id")
    private Long groupId;
    private String secret;

    public enum CallbackType {
        message_new, confirmation
    }
}
