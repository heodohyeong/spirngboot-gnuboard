package com.gnuboard.demo.user.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class MemberLoginDto {

    //@NotEmpty(message = "Email is a required input")
    private String userId;

    //@NotEmpty(message = "password is a required input")
    private String password;
}
