package com.gnuboard.demo.util.redis.booktest;


import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class BookService {


    private final BookRepository bookRepository;

    private final RedissonClient redissonClient;


    @Transactional
    public void purchase(Long bookId ,final Long quantity){

        Book book = bookRepository.findById(bookId)
                .orElseThrow(IllegalArgumentException::new);

        book.purchase(quantity);
        System.out.println("현재 재고 : "+ book.getStock());
        //bookRepository.save(book);

    }

    @Transactional
    public void purchase2(Long bookId ,final Long quantity){
        RLock lock = redissonClient.getLock(String.format("purchase:book:%d" , bookId));

        try{
            boolean available = lock.tryLock(10, 1, TimeUnit.SECONDS);
            if (!available) {
                System.out.println("redisson getLock timeout");
                return;
            }
            Book book = bookRepository.findById(bookId)
                    .orElseThrow(IllegalArgumentException::new);
            book.purchase(quantity);


        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }


        //bookRepository.save(book);

    }


}
