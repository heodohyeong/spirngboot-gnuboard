package com.gnuboard.demo.util.redis;


import com.google.api.client.auth.oauth2.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisService {

    private final RedisTokenRepository redisTokenRepository;


    // 토큰 저장
    @Transactional
    public void saveToken(Long userId, RedisTokenResponse token) {
        try {
            redisTokenRepository.save(RedisToken.builder()
                    .userId(userId)
                    .accessToken(token.getAccessToken())
                    .refreshToken(token.getRefreshToken())
                    .expiration(token.getExpiresIn())
                    .build());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    // 토큰 조회
    public RedisToken getRedisToken(Long userId) {
        try {
            return redisTokenRepository.findById(userId)
                    .orElseThrow(IllegalArgumentException::new) // 토큰이 존재하지 않은 경우
                    ;
        } catch (Exception e) {
            System.out.println("error");
            log.error(e.getMessage());
        }
        return null;
    }

    // 토큰 조회
    public String getAccessToken(Long userId) {
        try {
            return redisTokenRepository.findById(userId)
                    .orElseThrow(IllegalArgumentException::new) // 토큰이 존재하지 않은 경우
                    .getAccessToken();
        } catch (Exception e) {
            System.out.println("error");
            log.error(e.getMessage());
        }
        return null;
    }
    // 토큰 조회
    public String getRefreshToken(Long userId) {
        try {
            return redisTokenRepository.findById(userId)
                    .orElseThrow(IllegalArgumentException::new) // 토큰이 존재하지 않은 경우
                    .getRefreshToken();
        } catch (Exception e) {
            System.out.println("error");
            log.error(e.getMessage());
        }
        return null;
    }

    // 토큰 삭제
    @Transactional
    public void deleteToken(RedisToken token) {
        try {
            redisTokenRepository.delete(token);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
