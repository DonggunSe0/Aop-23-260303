package com.back;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class AppConfig {

    @Autowired
    @Lazy //지연로딩, 순환참조 방지
    private AppConfig self; //가짜 중국집 @lazy가 없으면 진짜 중국집이 된다. @lazy가 없으면 진짜 중국집이 된다.


    @Bean
    public ApplicationRunner myApplicationRunner3() {
        return args -> {

            //리얼 객체의 메서드 호출
            this.work1();
            this.work2();

            //프록시 객체의 메서드 호출
            self.work1();
            self.work2();
        };
    }

    @Transactional
    public void work1() {
        System.out.println("work1");
    }

    @Transactional
    public void work2() {
        System.out.println("work2");
    }

}