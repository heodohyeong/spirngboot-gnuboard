package com.gnuboard.demo.user.repository;

import com.gnuboard.demo.user.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUserId(String userId);

    Optional<Member> findByUserIdAndPassword(String userId , String password);
}
