package com.back;

import org.springframework.stereotype.Service;

@Service//Component 어노테이션을 사용하여 PersonService 클래스를 Bean으로 등록
//그래서 AppConfig 클래스에서 Bean으로 등록할 필요가 없어짐
public class PersonService {
    public int count() {
        return 3;
    }
}
