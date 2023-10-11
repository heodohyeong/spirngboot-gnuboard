package com.gnuboard.demo.user.adaptor;

import com.gnuboard.demo.user.domain.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MemberAdaptor extends User {

    private Member member;

    public MemberAdaptor(Member member){
        super(member.getUserId() , member.getPassword() , List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.member = member;
    }


}
