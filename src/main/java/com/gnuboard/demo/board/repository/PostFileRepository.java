package com.gnuboard.demo.board.repository;

import com.gnuboard.demo.board.domain.BoardFile;
import com.gnuboard.demo.board.domain.PostFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostFileRepository extends JpaRepository<PostFile,Long> {
}
