package com.gnuboard.demo.board.repository;

import com.gnuboard.demo.board.domain.BoardSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BoardSettingRepository extends JpaRepository<BoardSettings, Long> {

    List<BoardSettings> findAll();

    //BoardSettings findById();

}
