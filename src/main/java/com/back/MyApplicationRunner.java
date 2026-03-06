package com.back;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationRunner implements ApplicationRunner {
//ApplicationRunner에서는 run()메서드를 자동으로 실행
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("애플리케이션이 시작되었습니다!");
    }
}
