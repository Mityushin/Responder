package ru.mityushin.responder.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@Getter
public class MessageNewCallback {
    @Id
    private Long id;
    private Long date;
    private Long out;
    private Long userId;
    private Long readState;
    private String title;
    private String body;
    private Long groupId;
}
