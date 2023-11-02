package com.gnuboard.demo.board.repository;

import com.gnuboard.demo.board.domain.BoardFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardFileRepository extends JpaRepository<BoardFile,Long> {

    Optional<BoardFile> findById(Long id);
}
