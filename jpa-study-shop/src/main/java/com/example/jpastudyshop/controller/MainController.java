package com.example.jpastudyshop.controller;

import com.example.jpastudyshop.domain.Member;
import com.example.jpastudyshop.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @GetMapping("/member")
    public String saveMember() {
        Member member = new Member();
        member.setName("JM");
        mainService.saveMember(member);
        return "OK";
    }
}
