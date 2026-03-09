package com.back;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonRepository {

    public int count() {
        return 3;
    }

}
