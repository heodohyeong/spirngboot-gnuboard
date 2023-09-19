package com.gnuboard.demo.board.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController{


    @GetMapping("/board1")
    public String board(){
        return "board/board1";
    }


}
