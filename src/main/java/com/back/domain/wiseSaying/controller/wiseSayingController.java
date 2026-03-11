package com.back.domain.wiseSaying.controller;

import com.back.domain.wiseSaying.entity.WiseSaying;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class wiseSayingController {
    private final List<WiseSaying> wiseSayingList = new ArrayList<>();
    private int lastId = 0;

    //queryString 방식으로 명언 등록

    @GetMapping("/wiseSaying/write")
    @ResponseBody
    public String write(
            String content,
            String author
            //write?content=aaa&author=bbb 쪼개서 content=aaa, author=bbb
    ) { // 명언 등록 create

        if (content == null || content.trim().length() == 0) {
            throw new RuntimeException("명언을 입력해주세요.");
        } // 보통 스프링에서 유효성 체크를 예외처리로

        if (author == null || author.trim().length() == 0) {
            throw new RuntimeException("작가를 입력해주세요.");
        } // 보통 스프링에서 유효성 체크를 예외처리로

        WiseSaying wiseSaying = new WiseSaying(++lastId,content, author);
        wiseSayingList.add(wiseSaying);

        return "%d번 명언이 등록되었습니다.".formatted(wiseSaying.getId());
    }
    @GetMapping("/wiseSaying/list")
    @ResponseBody
    public String list() { //명언 목록
        String wiseSayings = wiseSayingList.stream()
                .map(w -> "<li>%s / %s / %s </li>".formatted(w.getId(), w.getContent(), w.getAuthor()))
                .collect(Collectors.joining("\n"));

        return """
                <ul>
                %s
                </ul>""".formatted(wiseSayings);
    }

    @GetMapping("/wiseSaying/delete/{id}") //변수화
    @ResponseBody // 삭제?id=1 -> v1
    public String delete(
            @PathVariable int id
    ) {
        Optional<WiseSaying> wiseSaying = wiseSayingList.stream()
                .filter(w->w.getId() == id)
                .findFirst();

        if (wiseSaying.isEmpty()) {
            throw new RuntimeException("%d번 명언은 존재하지 않습니다.".formatted(id));
        }

        wiseSayingList.remove(wiseSaying.get());

        return "%d번 명언이 삭제되었습니다.".formatted(id);


    }

}
