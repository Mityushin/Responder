package ru.mityushin.responder.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessageNewCallback {
    @Id
    private Long id;
    private Long date;
    private Long peerId;
    private Long fromId;
    private String text;
    private Long groupId;
}
