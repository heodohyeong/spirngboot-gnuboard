package com.gnuboard.demo.config;


import com.gnuboard.demo.board.domain.BoardFile;
import com.gnuboard.demo.board.domain.BoardPost;
import com.gnuboard.demo.board.domain.BoardSettings;
import com.gnuboard.demo.board.domain.PostFile;
import com.gnuboard.demo.board.repository.BoardFileRepository;
import com.gnuboard.demo.board.repository.BoardPostRepository;
import com.gnuboard.demo.board.repository.BoardSettingRepository;
import com.gnuboard.demo.board.repository.PostFileRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitConfig {


    private final BoardSettingRepository boardSettingRepository;
    private final BoardPostRepository boardPostRepository;
    private final BoardFileRepository boardFileRepository;
    private final PostFileRepository postFileRepository;

    @Transactional
    @PostConstruct
    public void init(){
        BoardSettings boardSettings = new BoardSettings();
        boardSettings.setCreatedAt(LocalDateTime.now());
        boardSettings.setTitle("test");
        boardSettings.setWriter("test1");
        boardSettings.setCreatedBy(Long.valueOf(1));
        //boardSettings.setId(Long.valueOf(1));

        BoardPost boardPost = new BoardPost();
        boardPost.setTitle("testtest");
        boardPost.setContent("content");
        boardPost.setBoardSettings(boardSettings);

        BoardFile boardFile = new BoardFile();
        boardFile.setContent("123");
        boardFile.setCreatedAt(LocalDateTime.now());
        boardFile.setDownload(0);
        boardFile.setFilesize(10);

        PostFile postFile = new PostFile();
        postFile.setBoardPost(boardPost);
        postFile.setBoardFile(boardFile);
        postFile.setDelYn("N");
        boardSettingRepository.save(boardSettings);
        boardPostRepository.save(boardPost);
        boardFileRepository.save(boardFile);
        postFileRepository.save(postFile);
//        em.persist(boardSettings);
//        em.persist(boardPost);
//        em.persist(boardFile);
//        em.persist(postFile);
    }
}
