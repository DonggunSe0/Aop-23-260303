package com.back.domain.wiseSaying.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class WiseSaying {

    private int id;
    private String content;
    private String author;

    public WiseSaying(String content, String author) {
        this.content = content;
        this.author = author;
    }

    public boolean isNew() {
        return this.id == 0;
    } // id가 0이면 새로운 명언, id가 0이 아니면 기존 명언

    public void update(String content, String author) {
        this.content = content;
        this.author = author;
    }
}
