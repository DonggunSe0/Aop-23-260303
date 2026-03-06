package com.back;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

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

    //ApplicationRunner 바로 만들기
//    @Bean
//    public ApplicationRunner myApplicationRunner3() {
//        return (ApplicationArguments args) -> {
//            work1();
//            work2();
//        }; //new ApplicationRunner() 생략가능
//    }
//
//    public void work1() {
//        System.out.println("work1");
//    }
//
//    public void work2() {
//        System.out.println("work2");
//
//    }
//}
//익명 객체인데 메서드가 하나인 인터페이스라서 람다식으로 표현할 수 있어. 그래서 new ApplicationRunner() 생략 가능해.

// ApplicationRunner 인터페이스를 구현한 익명 클래스를 Bean으로 등록하는 방법이야.
// 이렇게 하면 myApplicationRunner3라는 Bean이 생성되고,
// 스프링이 애플리케이션이 시작될 때 run() 메서드를 자동으로 실행하게 돼.


//    public ApplicationRunner myApplicationRunner() {

    /// /        return new ApplicationRunner(); //new가 안되는 이유 ->
//        // ApplicationRunner는 스프링에서 제공하는 인터페이스이기 때문에 직접 인스턴스를 생성할 수 없어.
//    }
    @Bean
    @Order(2) //ApplicationRunner의 실행 순서를 지정하는 어노테이션
    public ApplicationRunner myApplicationRunner1() {
        return new MyApplicationRunner(1);
    }

    @Bean
    @Order(1) //ApplicationRunner의 실행 순서를 지정하는 어노테이션
    public ApplicationRunner myApplicationRunner2() {
        return new MyApplicationRunner(2);
    }

}