package com.gnuboard.demo.board.domain;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class BoardSettings {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="board_setting_id")
    private Long id;

    @Column(name="board_setting_subject")
    private String title;

    @Column(name = "board_setting_writer")
    private String writer;

    @Column(name = "board_setting_created_at")
    private LocalDateTime createdAt;

    @Column(name = "board_setting_created_by")
    private Long createdBy;

    @Column(name = "board_setting_modifier_at")
    private LocalDateTime modifierAt;

    @Column(name = "board_setting_modified_by")
    private Long modifiedBy;
}
