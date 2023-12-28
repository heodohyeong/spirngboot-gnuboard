package com.gnuboard.demo.util.redis.booktest;


import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class BookLockFacade {

    private final BookService bookService;
    private final RedissonClient redissonClient;

    public void purchase(final Long bookId, final Long quantity) {
        System.out.println("string format");
        System.out.println(String.format("purchase:book:%d", bookId));
        RLock lock = redissonClient.getLock(String.format("purchase:book:%d", bookId));
        System.out.println("lock###################################################");
        System.out.println(lock);

        try {
            boolean available = lock.tryLock(10, 1, TimeUnit.SECONDS);
            System.out.println("available###################################################");
            System.out.println(available);
            if (!available) {
                System.out.println("redisson getLock timeout");
                throw new IllegalArgumentException();
            }
            bookService.purchase(bookId, quantity);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }


}
