package com.back.domain.wiseSaying.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;


@Getter
@Setter
@Entity // @Entity 어노테이션을 사용하여 JPA 엔티티로 지정 - 데이터베이스 테이블과 매핑
@NoArgsConstructor // 기본 생성자 자동 생성 - JPA에서 엔티티를 생성할 때 필요
public class WiseSaying {

    @Id
    @GeneratedValue(strategy = IDENTITY) // id 필드를 기본 키로 지정하고 자동으로 증가하도록 설정
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
