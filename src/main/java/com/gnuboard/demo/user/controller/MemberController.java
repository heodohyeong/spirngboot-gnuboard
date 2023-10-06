package com.gnuboard.demo.user.controller;


import com.gnuboard.demo.user.dto.MemberDto;
import com.gnuboard.demo.user.dto.MemberLoginDto;
import com.gnuboard.demo.user.service.MemberService;
import com.gnuboard.demo.util.jwt.JwtProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final JwtProvider jwtProvider;

    @Value("${jwt.cookie_name}")
    private String jwtCookieName;

    @GetMapping("login")
    public String login(Model model){
        model.addAttribute("loginDto" , new MemberLoginDto());
        return "member/login";
    }

    @PostMapping("loginAction")
//    public String loginAction(@RequestParam(name = "userId" ,required = true) String userId
//            ,@RequestParam(name = "password",required = true) String password){
    public String loginAction(@ModelAttribute MemberLoginDto memberLoginDto , HttpServletRequest request , HttpServletResponse response) throws UnsupportedEncodingException {
        MemberDto memberDto = memberService.findByUserId(memberLoginDto.getUserId());
        if(memberDto != null && bCryptPasswordEncoder.matches(memberLoginDto.getPassword(), memberDto.getPassword())){
            // token 발급
            log.info("아아디 : {} 님 접속" , memberLoginDto.getUserId());
            String token = jwtProvider.createToken(memberLoginDto.getUserId(),null);
            log.info("token : {} " , token);

            String test = URLEncoder.encode("bearer-"+token,"utf-8");
            log.info("token : {} " , test);
            //TODO "bearer " 넣어아햠
            Cookie cookie = new Cookie(jwtCookieName, token);
            cookie.setMaxAge(60*30); // 30분
            cookie.setPath("/");
            //Cookie[] userCookie = request.getCookies();
            response.addCookie(cookie);
        }
        return "redirect:/";
    }

    @GetMapping("registration")
    public String signin(){
        return "member/form";
    }


}
