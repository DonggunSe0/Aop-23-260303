package com.back;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
//Component 어노테이션을 사용하여 PersonService 클래스를 Bean으로 등록
//그래서 AppConfig 클래스에서 Bean으로 등록할 필요가 없어짐
//@Qualifier는 스프링기능이고 @RequiredArgsConstructor는 롬복기능이야.
// 그래서 @RequiredArgsConstructor는 생성자 만들어주는 역할을 하는데,
// 둘이 통합이 안돼서 안될 수도 있어서 생성자를 직접 만들어서 @Qualifier로 버전 관리하는 방법
public class PersonService {
    private final PersonRepository personRepository; //의존성 주입(DI)을 통해 PersonRepository 객체를 주입받음

    //Qualifier로 버전 관리 가능
    //Qualifier 어노테이션을 사용하여 어떤 Bean을 주입받을지 명시적으로 지정
    public PersonService(@Qualifier("personRepositoryV2") PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public int count() {
        return personRepository.count(); //PersonRepository의 count() 메서드를 호출하여 사람 수를 반환
    }
}
