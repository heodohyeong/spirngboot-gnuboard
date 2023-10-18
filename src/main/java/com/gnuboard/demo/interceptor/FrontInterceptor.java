package com.gnuboard.demo.interceptor;

import com.gnuboard.demo.util.jwt.JwtProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@RequiredArgsConstructor
@Component
@Slf4j
public class FrontInterceptor implements HandlerInterceptor {

    private final JwtProvider jwtProvider;

    @Value("${jwt.cookie_name}")
    private String jwtCookieName;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //log.info("-------------------요청 : {}"+request.getRequestURI());

        /*request.setAttribute("request",request);
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
                    }
                }
            }
        }*/




        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
