package com.gnuboard.demo.util.jwt.filter;

import com.gnuboard.demo.util.jwt.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;


@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    String[] excludePath = {"/global", "/portal2" , "/humanframe"};

    @Value("${jwt.cookie_name}")
    private String jwtCookieName;

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("JwtFilter start");
        log.info("-------------------요청 : {}",request.getRequestURI());


        request.setAttribute("request",request);
        Cookie[] cookies = request.getCookies();

        if(cookies!=null){
            for (Cookie c : cookies) {
                String name = c.getName(); // 쿠키 이름 가져오기
                String value = c.getValue(); // 쿠키 값 가져오기
                log.info("name : {}" , name);
                log.info("value : {}" , value);
                if (name.equals(jwtCookieName)) {
                    //  String token = value.split(" ")[1];
                    log.info("token : {}" , value);
                    //if(!jwtProvider.validateToken(value.replace("bearer ",""))){
                    if(!jwtProvider.validateToken(value)){
                        //토큰 만료
                        log.info("만료된 토큰입니다.");
                    }else{
                        String userId = jwtProvider.getUserId(value);
                        request.setAttribute("userId",userId);
                        Authentication authentication = jwtProvider.getAuthentication(value);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        //SecurityContextHolder.getContextHolderStrategy().getContext().setAuthentication(authentication);

                    }
                }
           }
        }


        log.info("JwtFilter end");
        filterChain.doFilter(request,response);
    }

    //정적리소스는 filter를 거치지 않기 위해 설정
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        String path = request.getRequestURI();
        return Arrays.stream(excludePath).anyMatch(path::startsWith);

        //return super.shouldNotFilter(request);
    }
}
