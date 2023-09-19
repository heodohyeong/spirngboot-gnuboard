package com.gnuboard.demo.config;

import com.gnuboard.demo.interceptor.FrontInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new FrontInterceptor())
                .excludePathPatterns("/global/**","/portal2/**");
    }
}
