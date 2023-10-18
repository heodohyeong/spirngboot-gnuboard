package com.gnuboard.demo.config;


import com.gnuboard.demo.util.jwt.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {

    //private final JwtTokenProvider jwtTokenProvider;

    private final JwtFilter jwtFilter;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //.csrf(AbstractHttpConfigurer::disable)
                //.csrf(csrfConfig -> csrfConfig.ignoringRequestMatchers("/h2-console/**"))
                /*.headers((headersConfig) ->
                            headersConfig.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable())
                        )*/
                //.headers(HeadersConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .headers(headersConfigurer -> headersConfigurer.frameOptions(frameOptionsConfig -> frameOptionsConfig.sameOrigin()))
                .authorizeHttpRequests(authorizeHttpRequestsConfig ->
                                authorizeHttpRequestsConfig.requestMatchers(
                                         //   new AntPathRequestMatcher("/member/login")
                                       new AntPathRequestMatcher("/h2-console/**")
                                        ,new AntPathRequestMatcher("/**")
                                        //,new AntPathRequestMatcher("/index")
                                ).permitAll().anyRequest().authenticated()
                        )
                //.httpBasic(withDefaults())
                .formLogin((formLoginConfig) ->
                            formLoginConfig.loginPage("/member/login").defaultSuccessUrl("/index")
                        )

                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)


        ;
        //하위 쓰레드에도 SecurityContextHolder 공유 설정
        //2023-10-12 설정사용시  @AuthenticationPrincipal 의 객체가 null 값으로 넘어옴
        //SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
        //SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_GLOBAL);
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_THREADLOCAL); //default
        return http.build();
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
//    }


    //정적리소스는 filter를 거치지 않기 위해 설정
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().requestMatchers(
                new AntPathRequestMatcher("/portal2/**"),
                new AntPathRequestMatcher("/global/**")
        );
    }

    @Bean
    public BCryptPasswordEncoder encoderPassword(){
        return new BCryptPasswordEncoder();
    }
}
