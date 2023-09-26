package com.gnuboard.demo.user.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("member")
public class MemberController {


    @GetMapping("login")
    public String login(){


        return "member/signin";
    }


}