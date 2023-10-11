package com.gnuboard.demo.util.jwt.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;


@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {

    String[] excludePath = {"/global", "/portal2"};


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("JwtFilter start");
        log.info("-------------------요청 : {}",request.getRequestURI());

        log.info("JwtFilter end");
        filterChain.doFilter(request,response);
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        String path = request.getRequestURI();
        return Arrays.stream(excludePath).anyMatch(path::startsWith);

        //return super.shouldNotFilter(request);
    }
}
