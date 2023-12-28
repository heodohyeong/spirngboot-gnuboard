package com.gnuboard.demo.util.redis;


import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@RedisHash("redisToken")
public class RedisToken {

    @Id
    Long userId;

    String accessToken;

    String refreshToken;

    @TimeToLive
    Integer expiration;

    @Builder
    public RedisToken(Long userId , String accessToken ,String refreshToken, Integer expiration){
        this.userId = userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiration = expiration;
    }

}
