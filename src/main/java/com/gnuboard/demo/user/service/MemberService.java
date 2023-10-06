package com.gnuboard.demo.user.service;

import com.gnuboard.demo.user.domain.Member;
import com.gnuboard.demo.user.dto.MemberDto;
import com.gnuboard.demo.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final ModelMapper modelMapper;

    public MemberDto findUserByCredentials(String userId , String password){
        Member member =  memberRepository.findByUserIdAndPassword(userId,password)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        MemberDto memberDto = modelMapper.map(member , MemberDto.class);
        return memberDto;
    }

    public MemberDto findByUserId(String userId) {
        Member member =  memberRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        MemberDto memberDto = modelMapper.map(member , MemberDto.class);
        return memberDto;
    }

}
