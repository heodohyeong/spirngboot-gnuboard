package com.gnuboard.demo.board.repository;

import com.gnuboard.demo.board.domain.BoardSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


@Repository
public interface BoardSettingRepository extends JpaRepository<BoardSettings, Long> {

    List<BoardSettings> findAll();

    Optional<BoardSettings> findById(Long id);

}
