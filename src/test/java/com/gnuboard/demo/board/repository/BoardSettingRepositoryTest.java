package com.gnuboard.demo.board.repository;

import com.gnuboard.demo.board.domain.*;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


//@SpringBootTest
@ActiveProfiles("test")
@DataJpaTest
class BoardSettingRepositoryTest {

    @Autowired
    private BoardSettingRepository boardSettingRepository;

    @Autowired
    private BoardPostRepository boardPostRepository;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("jpa 테스트")
    public void boardSettingRepository_test(){

        List<BoardSettings> list = boardSettingRepository.findAll();
System.out.println(list);

    }

    @Test
    @DisplayName("queryDSL 테스트")
    public void queryDSL_test(){
        JPAQuery<BoardSettings> query = new JPAQuery<>(em);
        QBoardSettings qBoardSettings = QBoardSettings.boardSettings;

        List<BoardSettings> list = query.from(qBoardSettings)
                .where(qBoardSettings.title.isEmpty()).fetch();

        assertEquals(list.size(),0);
    }

    @Test
    @DisplayName("데이터 등록 테스트")
    public void save_test(){

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

        em.persist(boardSettings);
        em.persist(boardPost);
        em.persist(boardFile);
        em.persist(postFile);

        em.flush();
        em.clear();


        BoardPost bp = em.find(BoardPost.class , 1);


        System.out.println(bp);

        for(PostFile pf : bp.getPostFileList()){

            System.out.print(pf.getBoardFile());

        }

        em.flush();
        em.clear();

        System.out.println("=========================================");

        List<BoardPost> bpList = boardPostRepository.findAll();

        System.out.println(bpList);
        for(BoardPost bp2 : bpList){

            System.out.println(bp2.getPostFileList());
            for(PostFile pf : bp2.getPostFileList()){
                System.out.println(pf.getBoardFile());
            }


        }
    }

}