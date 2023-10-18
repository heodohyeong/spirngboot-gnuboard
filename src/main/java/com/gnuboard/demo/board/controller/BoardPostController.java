package com.gnuboard.demo.board.controller;


import com.gnuboard.demo.board.domain.BoardPost;
import com.gnuboard.demo.board.domain.BoardSettings;
import com.gnuboard.demo.board.domain.QBoardSettings;
import com.gnuboard.demo.board.dto.BoardPostDto;
import com.gnuboard.demo.board.service.BoardPostService;
import com.gnuboard.demo.board.service.BoardSettingService;
import com.gnuboard.demo.user.adaptor.MemberAdaptor;
import com.gnuboard.demo.user.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board1")
public class BoardPostController {


    private final BoardPostService boardPostService;

    private final BoardSettingService boardSettingService;

    @GetMapping("/")
    public String board(Model model
            , @RequestParam(name = "bpId", defaultValue = "1") int bpId
            , @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        //Page<BoardPost> page =  boardPostService.pageList(pageable);
        Page<BoardPost> page = boardPostService.pageSearchList(bpId, pageable);
        log.info("시작 페이지 [{}]", pageable.getPageNumber());
        log.info("총페이지 [{}]", page.getTotalPages());
        model.addAttribute("post", page);
        model.addAttribute("searchBpId", bpId);
        return "board/board1";
    }


    @GetMapping("/form")
    public String form(Model model, @AuthenticationPrincipal MemberAdaptor memberAdaptor
                       , Principal principal , HttpServletRequest request
   ) {
        log.info("-------------------요청 : {}",request.getRequestURI());
        SecurityContextHolderStrategy authentication2 = SecurityContextHolder.getContextHolderStrategy();
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        log.info("{}",authentication);
        log.info("{}",authentication.getPrincipal());
        log.info("authentication2 {}",authentication2);
        log.info("authentication2 {}",authentication2.getContext().getAuthentication());

        if(memberAdaptor != null){
            log.info("authentication : {}" , memberAdaptor.getMember().getUserId());
        }else{
            log.info("authentication : null");
        }

        if(principal !=null){
            log.info("principal : {}",principal);
        }else{
            log.info("principal : null");
        }

        BoardPostDto boardPostDto = new BoardPostDto();

        model.addAttribute("boardPostDto", boardPostDto);
        return "board/form";
    }


    @PostMapping("/insertBoard")
    public String insertBoard(@ModelAttribute BoardPostDto boardPostDto, @RequestPart(value="file" ,required = false) List<MultipartFile> files
                            ) {
        //
        //BoardSettings boardSettings = boardSettingService.findById(Long.valueOf(1));



        //boardPostDto.setBoardSettings(boardSettings);
        // 게시글 등!!록
        //BoardPost boardPost = boardPostService.insertBoard(boardPostDto.toEntity());
        //log.info("게시글 등록 번호 : {}", boardPost.getId());
        //첨부파일 등록
        //log.info(multipartFile.getName());
        if(files != null){
            for(MultipartFile mf : files){
                log.info(mf.getName());
                log.info(mf.getOriginalFilename());
            }
        }else{
            log.info("files is null");
        }

        return "redirect:/board1/form";
    }
}