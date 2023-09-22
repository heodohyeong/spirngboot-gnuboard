package com.gnuboard.demo.board.domain;


import com.gnuboard.demo.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class BoardPost extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bp_id")
    private Long id;

    @Column(name="bp_title")
    private String title;

    @Column(name="bp_content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bs_id")
    private BoardSettings boardSettings;


    @OneToMany(mappedBy = "boardPost")
    private List<PostFile> postFileList = new ArrayList<>();


    @Override
    public String toString() {
        return "BoardPost{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", boardSettings=" + boardSettings +
                '}';
    }
}
