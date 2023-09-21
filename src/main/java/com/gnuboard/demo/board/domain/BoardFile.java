package com.gnuboard.demo.board.domain;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class BoardFile {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bf_id")
    private Long id;

    @Column(name = "bf_source")
    private String source;

    @Column(name = "bf_file")
    private String file;

    @Column(name = "bf_download")
    private int download;

    @Column(name = "bf_content")
    private String content;

    @Column(name = "bf_filesize")
    private int filesize;

    @Column(name = "bf_created_at")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bs_id" , foreignKey = @ForeignKey(name = "fk_bs_id"))
    private BoardSettings boardSettings;



}
