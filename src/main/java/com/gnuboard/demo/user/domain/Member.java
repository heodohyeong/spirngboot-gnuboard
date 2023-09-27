package com.gnuboard.demo.user.domain;


import com.gnuboard.demo.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity implements UserDetails {

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



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
