package com.gnuboard.demo.index;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request){
        //model.addAttribute("request", request);
        return "index";
    }
}
