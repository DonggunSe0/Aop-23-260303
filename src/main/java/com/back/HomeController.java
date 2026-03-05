package com.back;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    private PersonService personService = new PersonService();

//    @GetMapping("/home")은 사용자가 브라우저에서 /home 주소로 GET 요청을 보내면,
//    그 아래 메서드를 실행하게 연결해주는 거야.
    @GetMapping("/home")
    @ResponseBody //@ResponseBody를 붙이면 return한 값이 브라우저에 그대로 표시돼.
    public String home() {
        return  "사람 수 : %d".formatted(personService.count());
    }
}
