package com.gnuboard.demo.board.repository;

import com.gnuboard.demo.board.domain.BoardPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardPostRepository extends JpaRepository<BoardPost, Long> {

    List<BoardPost> findAll();

}
