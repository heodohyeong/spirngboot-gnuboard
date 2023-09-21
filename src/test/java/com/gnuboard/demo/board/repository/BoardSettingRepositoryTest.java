package com.gnuboard.demo.board.repository;

import com.gnuboard.demo.board.domain.BoardFile;
import com.gnuboard.demo.board.domain.BoardSettings;
import com.gnuboard.demo.board.domain.QBoardSettings;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


//@SpringBootTest
@ActiveProfiles("test")
@DataJpaTest
class BoardSettingRepositoryTest {

    @Autowired
    private BoardSettingRepository boardSettingRepository;


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

        BoardFile boardFile = new BoardFile();
        boardFile.setContent("123");
        boardFile.setCreatedAt(LocalDateTime.now());
        boardFile.setDownload(0);
        boardFile.setFilesize(10);

        boardSettings.addFile(boardFile);
        em.persist(boardFile);
        System.out.println("============================ : "+boardFile.getId());

        boardSettingRepository.save(boardSettings);

        List<BoardSettings> list = (List<BoardSettings>) em.createQuery("select b from BoardSettings b" , BoardSettings.class).getResultList();

        List<BoardFile> list2 = (List<BoardFile>) em.createQuery("select b from BoardFile b" , BoardFile.class).getResultList();

        System.out.println(list);
        System.out.println(list.get(0).getFileList());

        System.out.println(list2);



    }

}