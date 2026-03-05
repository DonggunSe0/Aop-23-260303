package com.back;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean // Bean으로 PersonService 객체를 등록
    public PersonService personService() {
        System.out.println("AppConfig.personService() 호출");
        return new PersonService();
    }
}
