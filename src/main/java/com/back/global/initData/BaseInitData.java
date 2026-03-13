package com.back.global.initData;

import com.back.domain.member.entity.Member;
import com.back.domain.member.service.MemberService;
import com.back.domain.wiseSaying.service.WiseSayingService;
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
    private final WiseSayingService wiseSayingService;

    @Bean
    ApplicationRunner initDataRunner() {
        return args -> {
            self.work1();
            self.work2();
            work3();
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

    //WiseSayingRepository.save() 메서드가 자체적으로 트랜잭션이 있어서, save() 메서드가 실행될 때 트랜잭션이 자동으로 시작되고,
    // 메서드가 끝나면 트랜잭션이 커밋돼. 그래서 별도의 @Transactional 어노테이션이 없어도 돼.
    //@Trasactional이 하나의 작업 단위를 정의하는 어노테이션이야. 트랜잭션이 시작되면, 그 안에서 수행되는 모든 작업은
    // 하나의 단위로 묶여서 처리돼. 만약 그 안에서 예외가 발생하면, 트랜잭션 전체가 롤백돼서, 데이터의 일관성이 유지돼.
    void work3() {
        {
            if(wiseSayingService.count() > 0) {
                return;
            } //중복 방지라기보다 “초기 데이터가 이미 있으면 더 추가하지 않도록 막는 코드”

            wiseSayingService.write( "명언1", "작가1");
            wiseSayingService.write("명언2", "작가2");
            wiseSayingService.write("명언3", "작가3");
            wiseSayingService.write( "명언4", "작가4");
            wiseSayingService.write("""
                        -너 자신을 알라
                        -나의 죽음을 적에게 알리지 말라
                    """, "작가5");
        }; // array list에 초기값 넣는 방법, 초기값이 5개 들어가 있음
    }

}
