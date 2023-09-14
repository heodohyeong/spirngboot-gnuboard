package com.gnuboard.demo.user.domain;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;


    @Column(name = "member_user_id",length = 255)
    private String userId;

    @Column(name = "member_password" ,length = 255)
    private String password;
    @Column(name = "member_name" ,length = 255)
    private String name;


    @Column(name = "member_nick" , length = 255)
    private String nick;


    @Column(name = "member_email" , length = 255)
    private String email;

    @Column(name = "member_level" , length = 255)
    private int level;


    @Column(name = "member_sex" , length = 1)
    private String sex;


    @Column(name = "member_birth" , length = 255)
    private String birth;


}
