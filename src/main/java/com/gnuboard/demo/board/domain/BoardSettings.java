package com.gnuboard.demo.board.domain;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
public class BoardSettings {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bs_id")
    private Long id;

    @Column(name="bs_subject")
    private String title;

    @Column(name = "bs_writer")
    private String writer;

    @Column(name = "bs_created_at")
    private LocalDateTime createdAt;

    @Column(name = "bs_created_by")
    private Long createdBy;

    @Column(name = "bs_modifier_at")
    private LocalDateTime modifierAt;

    @Column(name = "bs_modified_by")
    private Long modifiedBy;

    @OneToMany(mappedBy = "boardSettings")
    private List<BoardFile> fileList = new ArrayList<>();

    public void addFile(BoardFile boardFile){
        fileList.add(boardFile);
        boardFile.setBoardSettings(this);
    }

    @Override
    public String toString() {
        return "BoardSettings{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", writer='" + writer + '\'' +
                ", createdAt=" + createdAt +
                ", createdBy=" + createdBy +
                ", modifierAt=" + modifierAt +
                ", modifiedBy=" + modifiedBy +
                '}';
    }
}
