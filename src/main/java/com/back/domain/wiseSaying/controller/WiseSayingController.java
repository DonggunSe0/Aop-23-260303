package com.back.domain.wiseSaying.controller;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.service.WiseSayingService;
import com.back.standard.MarkdownService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor // final이 붙은 필드의 생성자를 만들어주는 어노테이션, 생성자 주입 방식으로 의존성 주입을 할 때 사용
public class WiseSayingController {
    private final WiseSayingService wiseSayingService;
    private final MarkdownService markdownService;

    //queryString 방식으로 명언 등록
    @GetMapping("/wiseSayings/write")
    @ResponseBody
    public String write(
            String content,
            String author
            //write?content=aaa&author=bbb 쪼개서 content=aaa, author=bbb
    ) {
        // 명언 등록 create

        if (content == null || content.trim().length() == 0) {
            throw new RuntimeException("명언을 입력해주세요.");
        } // 보통 스프링에서 유효성 체크를 예외처리로

        if (author == null || author.trim().length() == 0) {
            throw new RuntimeException("작가를 입력해주세요.");
        } // 보통 스프링에서 유효성 체크를 예외처리로

        WiseSaying wiseSaying = wiseSayingService.write(content, author);

        return "%d번 명언이 등록되었습니다.".formatted(wiseSaying.getId());
    }
    @GetMapping("/wiseSayings")
    @ResponseBody
    public String list() { //명언 목록
        String wiseSayings = wiseSayingService.findAll().stream()
                .map(w -> "<li>%s / %s / %s </li>".formatted(w.getId(), w.getContent(), w.getAuthor()))
                .collect(Collectors.joining("\n"));

        return """
                <ul>
                %s
                </ul>""".formatted(wiseSayings);
    }


    @GetMapping("/wiseSayings/{id}") //주소체계
    @ResponseBody
    public String detail(
            @PathVariable int id

    ) {
        WiseSaying wiseSaying = wiseSayingService.findById(id);
        String html = markdownService.toHtml(wiseSaying.getContent());


        return """
                <h1>번호 : %s</h1>
                <div>명언 : %s</div>
                <div>작가 : %s</div>
                """.formatted(wiseSaying.getId(), html, wiseSaying.getAuthor());
    }

    @GetMapping("/wiseSayings/delete/{id}") //변수화
    @ResponseBody // 삭제?id=1 -> v1
    public String delete(
            @PathVariable int id
    ) {
        WiseSaying wiseSaying = wiseSayingService.findById(id);

     //   wiseSayingList.remove(wiseSaying);
        wiseSayingService.delete(wiseSaying);

        return "%d번 명언이 삭제되었습니다.".formatted(id);


    }
//update 수정기능 구현
    @GetMapping("/wiseSayings/modify/{id}")
    @ResponseBody
    public String modify(
            @PathVariable int id,
            @RequestParam(defaultValue = "기본값") String content,
            @RequestParam(defaultValue = "기본값") String author
            //예외 처리 대신 defaultValue를 기본값으로 주고 할 수 있음
    ) {
//        if (content == null || content.trim().length() == 0) {
//        throw new RuntimeException("명언을 입력해주세요.");
//        } // 보통 스프링에서 유효성 체크를 예외처리로
//
//        if (author == null || author.trim().length() == 0) {
//            throw new RuntimeException("작가를 입력해주세요.");
//        } // 보통 스프링에서 유효성 체크를 예외처리로


        WiseSaying wiseSaying = wiseSayingService.findById(id);
        wiseSayingService.modify(wiseSaying, content, author);

        return "%d번 명언이 수정되었습니다.".formatted(id);
    }



}
