package com.gnuboard.demo.board.domain;


import com.gnuboard.demo.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.*;


@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BoardSettings extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bs_id")
    private Long id;

    @Column(name="bs_subject")
    private String title;

    @Column(name = "bs_writer")
    private String writer;

    @Override
    public String toString() {
        return "BoardSettings{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", writer='" + writer + '\'' +
                '}';
    }
}
