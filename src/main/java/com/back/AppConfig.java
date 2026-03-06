package com.back;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //configuration 어노테이션을 사용하여 AppConfig 클래스를 설정 클래스(Bean 설정 클래스)로 지정
public class AppConfig {
    //PersonRepository에 component이용하지 않고 버전 관리를 위해서 Bean으로 등록
    //그러므로 상황에서 맞게 Componet를 쓸지 Bean으로 등록할지 선택하면 될듯
    @Bean
    public PersonRepository personRepository() {
        return new PersonRepository(1);
    }

    @Bean
    public PersonRepository personRepositoryV2() {
        return new PersonRepository(2);
    }

    //PersonService 클래스에 @Component 어노테이션이 붙어 있기 때문에
    // AppConfig 클래스에서 Bean으로 등록할 필요가 없어짐
//    @Bean // Bean으로 PersonService 객체를 등록
//    public PersonService personService() {
//        System.out.println("AppConfig.personService() 호출");
//        return new PersonService();
//    }

//    public ApplicationRunner myApplicationRunner() {
////        return new ApplicationRunner(); //new가 안되는 이유 ->
//        // ApplicationRunner는 스프링에서 제공하는 인터페이스이기 때문에 직접 인스턴스를 생성할 수 없어.
//    }
}
