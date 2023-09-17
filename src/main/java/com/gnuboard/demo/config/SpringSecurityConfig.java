package com.gnuboard.demo.config;


import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {



    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //.csrf(AbstractHttpConfigurer::disable)
                /*.headers((headersConfig) ->
                            headersConfig.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable())
                        )*/
                //.headers(HeadersConfigurer::disable)
                .headers(headersConfigurer -> headersConfigurer.frameOptions(frameOptionsConfig -> frameOptionsConfig.sameOrigin()))
                .authorizeHttpRequests(authorizeHttpRequestsConfig ->
                                authorizeHttpRequestsConfig.requestMatchers(
                                         //   new AntPathRequestMatcher("/member/login")
                                       //,new AntPathRequestMatcher("/h2-console/**")
                                        new AntPathRequestMatcher("/**")
                                        //,new AntPathRequestMatcher("/index")
                                ).permitAll().anyRequest().authenticated()
                        )
                //.httpBasic(withDefaults())
                .formLogin((formLoginConfig) ->
                            formLoginConfig.loginPage("/member/login").defaultSuccessUrl("/index")
                        )


        ;
        return http.build();
    }
}
