package com.back.domain.member.entity;

import com.back.global.jpa.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member extends BaseEntity {
    @Id
    private Long id;
    private String username;
    private String nickname;


    public Member(String username, String nickname) {
        this.username = username;
        this.nickname = nickname;
    }
}
