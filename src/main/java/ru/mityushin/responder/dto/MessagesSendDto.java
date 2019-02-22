package ru.mityushin.responder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Data transfer object for VK API {@code Messages.send} request
 *
 * @author Dmitry Mityushin
 * @see <a href="https://vk.com/dev/messages.send">https://vk.com/dev/messages.send</a>
 * @since 1.0
 */
@Builder
@Getter
@Setter
@JsonPropertyOrder(alphabetic = true)
@EqualsAndHashCode(exclude = "randomId")
public class MessagesSendDto implements Serializable {
    @JsonProperty(value = "user_id")
    Long userId;
    @JsonProperty(value = "random_id")
    Long randomId;
    @JsonProperty(value = "peer_id")
    Long peerId;
    String domain;
    @JsonProperty(value = "chat_id")
    Long chatId;
    @JsonProperty(value = "user_ids")
    List<Long> userIds;
    String message;
    Double lat;
    @JsonProperty(value = "long")
    Double longField;
    String attachment;
    @JsonProperty(value = "reply_to")
    Long replyTo;
    @JsonProperty(value = "forward_messages")
    String forwardMessages;
    @JsonProperty(value = "sticker_id")
    Long stickerId;
    @JsonProperty(value = "group_id")
    Long groupId;
    Map<String, String> keyboard;
    Long payload;
    @JsonProperty(value = "dont_parse_links")
    Integer dontParseLinks;
}
