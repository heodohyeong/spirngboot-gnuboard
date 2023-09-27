package com.gnuboard.demo.user.controller;


import com.gnuboard.demo.user.domain.Member;
import com.gnuboard.demo.user.dto.MemberDto;
import com.gnuboard.demo.user.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("login")
    public String login(){
        return "member/signin";
    }

    @GetMapping("login/action")
    public String loginAction(@RequestParam(name = "userId" ,required = true) String userId
            ,@RequestParam(name = "password",required = true) String password){
        MemberDto memberDto = memberService.findUserByCredentials(userId , bCryptPasswordEncoder.encode(password));
        if(memberDto != null){
            // token 발급
        }
        return "index";
    }

    @GetMapping("signin")
    public String signin(){
        return "member/signup";
    }


}
