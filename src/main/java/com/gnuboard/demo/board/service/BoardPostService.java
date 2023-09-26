package com.gnuboard.demo.board.service;


import com.gnuboard.demo.board.domain.BoardPost;
import com.gnuboard.demo.board.repository.BoardPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardPostService {

    @Autowired
    private final BoardPostRepository boardPostRepository;

    public Page<BoardPost> pageList(Pageable pageable){
        return boardPostRepository.findAll(pageable);
    }

    public Page<BoardPost> pageSearchList(int bsId ,Pageable pageable ){
        return boardPostRepository.findByBoardSettings_Id(bsId,pageable);
    }

}
