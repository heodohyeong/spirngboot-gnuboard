package com.gnuboard.demo.board.controller;


import com.gnuboard.demo.board.domain.BoardPost;
import com.gnuboard.demo.board.dto.BoardPostDto;
import com.gnuboard.demo.board.service.BoardPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardPostController {

    @Autowired
    private final BoardPostService boardPostService;

    @GetMapping("/board1")
    public String board(Model model
            , @RequestParam(name = "bpId" ,defaultValue = "1") int bpId
            , @PageableDefault(sort="id" , direction = Sort.Direction.DESC) Pageable pageable
                        ){
        //Page<BoardPost> page =  boardPostService.pageList(pageable);
        Page<BoardPost> page =  boardPostService.pageSearchList(bpId,pageable);
        log.info("시작 페이지 [{}]" ,pageable.getPageNumber());
        log.info("총페이지 [{}]" ,page.getTotalPages());
        model.addAttribute("post", page);
        model.addAttribute("searchBpId", bpId);
        return "board/board1";
    }


    @GetMapping("/board1/form")
    public String form(Model model ){

        BoardPostDto boardPostDto = new BoardPostDto();

        model.addAttribute("boardPostDto", boardPostDto);
        return "board/form";
    }


}
