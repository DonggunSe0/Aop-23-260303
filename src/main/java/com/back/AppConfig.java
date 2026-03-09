package com.back;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ApplicationRunner myApplicationRunner3() {
        return args -> {
            work1();
            work2();
        };
    }

    public void work1() {
        System.out.println("work1");
    }

    public void work2() {
        System.out.println("work2");
    }

}