package com.gnuboard.demo.user.dto;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    private Long id;


    private String userId;

    private String password;
    private String name;


    private String nick;


    private String email;

    private int level;


    private String sex;


    private String birth;


}
