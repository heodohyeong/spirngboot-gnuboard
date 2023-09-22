package com.gnuboard.demo.board.repository;

import com.gnuboard.demo.board.domain.BoardFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardFileRepository extends JpaRepository<BoardFile,Long> {
}
