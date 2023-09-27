package com.gnuboard.demo.board.repository;

import com.gnuboard.demo.board.domain.BoardPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardPostRepository extends JpaRepository<BoardPost, Long> {

    List<BoardPost> findAll();

    Page<BoardPost> findById(int bsId, Pageable pageable);

    Page<BoardPost> findByBoardSettings_Id(int bsId, Pageable pageable);
}
