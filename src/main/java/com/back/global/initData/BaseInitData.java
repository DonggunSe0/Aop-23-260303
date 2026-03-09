package com.back.global.initData;

import com.back.domain.member.entity.Member;
import com.back.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {

    @Autowired
    @Lazy
    private BaseInitData self;

    private final MemberService memberService;

    @Bean
    ApplicationRunner initDataRunner() {
        return args -> {
            self.work1();
            this.work2();
            //transactional이 반영이 되려면 프록시 객체를 통해서 메서드를 호출해야 해. 그래서 self.work1()이야. this.work1()은 프록시 객체가 아니라, 진짜 객체의 메서드를 호출하는 거야.
            // 그래서 transactional이 반영이 안돼. self.work1()은 프록시 객체의 메서드를 호출하는 거야. 그래서 transactional이 반영돼.
        };
    }

    // 생성
    @Transactional
    void work1() {

        if (memberService.count() > 0) {
            return;
        }

        Member member1 = memberService.join("systemUser", "시스템");
        Member member2 = memberService.join("adminUser", "관리자");
        Member member3 = memberService.join("user1", "유저1");
        Member member4 = memberService.join("user2", "유저2");
        Member member5 = memberService.join("user3", "유저3");


    }

    @Transactional //더티 체킹이 일어나려면 트랜잭션이 필요해.
        // 트랜잭션이 없으면, 영속성 컨텍스트가 없어서, 더티 체킹이 일어나지 않아.
    void work2() {
        Member member4 = (Member) memberService.findByUsername("user2").get();
        member4.setNickname("new user2");
        // 더티 체킹이 일어나서, 트랜잭션이 끝날 때, 변경된 member4 객체가 자동으로 DB에 반영돼.
    }

}
