package com.gnuboard.demo.util.jwt;


import com.gnuboard.demo.user.adaptor.MemberAdaptor;
import com.gnuboard.demo.user.domain.Member;
import com.gnuboard.demo.user.repository.MemberRepository;
import com.gnuboard.demo.user.service.CustomMemberDetailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtProvider {


    @Value("${jwt.secret_key}")
    private String secretKey = "";

    //토큰 유효시간 30분
    private long tokenValidTime = 30 * 60 * 1000L;


    private final UserDetailsService userDetailsService ;

    private final CustomMemberDetailService customMemberDetailService;

    @PostConstruct
    protected void init(){
        log.info("secretKey : {}" ,secretKey);
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        log.info("secretKey 암호화 : {}" ,secretKey);
    }

    public String createToken(String userId , List<String> roles){
        Claims claims = Jwts.claims().setSubject(userId);
        if(roles != null){
            claims.put("role" , roles);
        }
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)//정보저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime()+tokenValidTime)) //만료시간
                //.signWith(SignatureAlgorithm.ES256,secretKey) // 암호화 알고리즘
                .signWith(SignatureAlgorithm.HS256,secretKey) // 암호화 알고리즘
                .compact();


    }

    public Authentication getAuthentication(String token){
        //UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserId(token));
        MemberAdaptor memberAdaptor = (MemberAdaptor) customMemberDetailService.loadUserByUsername(this.getUserId(token));

        return new UsernamePasswordAuthenticationToken(memberAdaptor, token, memberAdaptor.getAuthorities());
    }

    public String getUserId(String token){
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
    }


    public String resolveToken(HttpServletRequest request){
        return request.getHeader("Authorization");
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
