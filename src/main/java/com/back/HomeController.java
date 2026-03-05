package com.back;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
//의존성 주입(DI)은 쉽게 말해 **“내가 필요한 객체를 내가 직접 만들지 않고,
//스프링이 대신 만들어서 넣어주는 것
public class HomeController {

    private final PersonService personService; //주입받은 의존성을 고정해서 안전하게 쓰려고

//    @GetMapping("/home")은 사용자가 브라우저에서 /home 주소로 GET 요청을 보내면,
//    그 아래 메서드를 실행하게 연결해주는 거야.
    @GetMapping("/home")
    @ResponseBody //@ResponseBody를 붙이면 return한 값이 브라우저에 그대로 표시돼.
    public String home() {
        return  "사람 수 : %d".formatted(personService.count());
    }
}
