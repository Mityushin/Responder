package ru.mityushin.responder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessagesSendResultDto {
    @JsonProperty(value = "peer_id")
    Long peerId;
    @JsonProperty(value = "message_id")
    Long messageId;
    MessagesSendErrorResultDto error;

    @Getter
    public static class MessagesSendErrorResultDto {
        @JsonProperty(value = "error_code")
        Long errorCode;
        @JsonProperty(value = "error_msg")
        String errorMsg;
//        @JsonProperty(value = "request_params")
//        Map<String, String> requestParams;
    }
}
