package com.gnuboard.demo.board.repository;

import com.gnuboard.demo.board.domain.BoardSettings;
import com.gnuboard.demo.board.domain.QBoardSettings;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BoardSettingRepositoryTest {

    @Autowired
    private BoardSettingRepository boardSettingRepository;


    @Autowired
    EntityManager em;

    @Test
    public void boardSettingRepository_test(){

        List<BoardSettings> list = boardSettingRepository.findAll();

        System.out.println(list);

    }

    @Test
    public void queryDSL_test(){
        JPAQuery<BoardSettings> query = new JPAQuery<>(em);
        QBoardSettings qBoardSettings = QBoardSettings.boardSettings;

        List<BoardSettings> list = query.from(qBoardSettings)
                .where(qBoardSettings.title.isEmpty()).fetch();

        assertEquals(list.size(),0);

    }

}