package com.gnuboard.demo.util.redis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class RedisServiceTest {

    @Autowired
    RedisService redisService;



    @Test
    @DisplayName("테스트")
    public void redisTest(){
        RedisTokenResponse redisTokenResponse = new RedisTokenResponse();
        redisTokenResponse.setAccessToken("accessToken");
        redisTokenResponse.setRefreshToken("refreshToken");
        redisTokenResponse.setTokenType("user");
        redisTokenResponse.setExpiresIn(10);
        redisTokenResponse.setRefreshTokenExpiresIn(10);

        redisService.saveToken(
                Long.valueOf(1)
                ,redisTokenResponse );


        String accessToken = redisService.getAccessToken(Long.valueOf(1));
        String refreshToken = redisService.getRefreshToken(Long.valueOf(1));
        RedisToken redisToken = redisService.getRedisToken(Long.valueOf(1));


        System.out.println(redisToken);
        System.out.println(redisToken.getAccessToken());
        System.out.println(redisToken.getRefreshToken());
        System.out.println(redisToken.getExpiration());
        System.out.println(redisToken.getUserId());


        System.out.println(accessToken);
        System.out.println(refreshToken);
        assertEquals(accessToken , "accessToken");
        assertEquals(refreshToken , "refreshToken");

    }


}