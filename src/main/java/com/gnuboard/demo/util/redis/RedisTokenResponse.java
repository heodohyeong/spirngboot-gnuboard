package com.gnuboard.demo.util.redis;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@Getter
@ToString
@Setter
public class RedisTokenResponse {

    @JsonProperty("token_type")
    String tokenType;

    @JsonProperty("access_token")
    String accessToken;

    @JsonProperty("refresh_token")
    String refreshToken;

    @JsonProperty("expires_in")
    Integer expiresIn;

    @JsonProperty("refresh_token_expires_in")
    Integer refreshTokenExpiresIn;

    String scope;
}
