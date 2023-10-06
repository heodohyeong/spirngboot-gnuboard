package com.gnuboard.demo.config;

import com.gnuboard.demo.interceptor.FrontInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final FrontInterceptor frontInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new FrontInterceptor())
        registry.addInterceptor(frontInterceptor)
                .excludePathPatterns("/global/**","/portal2/**" , "/humanframe/**","/error");
        //css 에서 불러오는 정적파일 경로가 잘못된 경우가 있어 /error ,/humanframe 제외 설정 추가
    }


}
