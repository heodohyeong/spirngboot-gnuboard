package com.gnuboard.demo.user.domain;


import com.gnuboard.demo.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity{

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


    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();




}
