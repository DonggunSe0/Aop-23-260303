package com.back;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

@RequiredArgsConstructor
public class MyApplicationRunner implements ApplicationRunner {
//ApplicationRunner에서는 run()메서드를 자동으로 실행
    private final int version;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("MyApplicationRunner.run()" + " version : " + version);
    }



}
