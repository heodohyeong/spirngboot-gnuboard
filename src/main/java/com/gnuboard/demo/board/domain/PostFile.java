package com.gnuboard.demo.board.domain;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PostFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pf_id")
    private Long id;


    @Column(name="pf_del_yn")
    private String delYn;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bp_id")
    private BoardPost boardPost;

    @OneToOne
    @JoinColumn(name = "bf_id")
    private BoardFile boardFile;




}
