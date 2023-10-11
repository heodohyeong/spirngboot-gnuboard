package com.gnuboard.demo.board.dto;

import com.gnuboard.demo.board.domain.BoardPost;
import com.gnuboard.demo.board.domain.BoardSettings;
import com.gnuboard.demo.board.domain.PostFile;
import com.gnuboard.demo.user.domain.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardPostDto {
    private Long id;

    private String title;

    private String content;

    private String writer;

    private Member member;

    private BoardSettings boardSettings;

    private List<PostFile> postFileList = new ArrayList<>();

    public BoardPost toEntity(){
        return BoardPost.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .member(member)
                .boardSettings(boardSettings)
                .build();
    }

}
