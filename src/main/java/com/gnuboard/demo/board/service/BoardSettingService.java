package com.gnuboard.demo.board.service;


import com.gnuboard.demo.board.domain.BoardSettings;
import com.gnuboard.demo.board.repository.BoardSettingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BoardSettingService {

    final BoardSettingRepository boardSettingRepository;

    public BoardSettings findById(Long id){
        Optional<BoardSettings> optionalBoardSettings = boardSettingRepository.findById(id);

        return optionalBoardSettings.get();
    }

}
