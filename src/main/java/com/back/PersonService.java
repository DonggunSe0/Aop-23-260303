package com.back;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor//Component 어노테이션을 사용하여 PersonService 클래스를 Bean으로 등록
//그래서 AppConfig 클래스에서 Bean으로 등록할 필요가 없어짐
public class PersonService {
    private final PersonRepository personRepository; //의존성 주입(DI)을 통해 PersonRepository 객체를 주입받음

    public int count() {
        return personRepository.count(); //PersonRepository의 count() 메서드를 호출하여 사람 수를 반환
    }
}
