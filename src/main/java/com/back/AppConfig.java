package com.back;

import org.springframework.context.annotation.Configuration;

@Configuration //configuration 어노테이션을 사용하여 AppConfig 클래스를 설정 클래스(Bean 설정 클래스)로 지정
public class AppConfig {





    //PersonService 클래스에 @Component 어노테이션이 붙어 있기 때문에
    // AppConfig 클래스에서 Bean으로 등록할 필요가 없어짐
//    @Bean // Bean으로 PersonService 객체를 등록
//    public PersonService personService() {
//        System.out.println("AppConfig.personService() 호출");
//        return new PersonService();
//    }
}
