package com.gnuboard.demo.board.domain;


import com.gnuboard.demo.common.domain.BaseEntity;
import com.gnuboard.demo.user.domain.Member;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardPost extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bp_id")
    private Long id;

    @Column(name="bp_title")
    private String title;

    @Column(name="bp_content")
    private String content;

    @Column(name = "bp_writer")
    private String writer;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

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
