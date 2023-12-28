package com.gnuboard.demo.util.redis.booktest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
class BookServiceTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @Autowired
    BookLockFacade bookLockFacade;

    @Test
    public void bookSave(){
        Book book = Book.builder()
                .bookName("testbook")
                .price(10000)
                .stock(Long.valueOf(100)).build();

        bookRepository.save(book);

        System.out.println(book.getId());

        Optional<Book> book2 = bookRepository.findById(book.getId());

        //assertThat(book.getId()).isEqualTo(book2.get().getId());
        assertEquals(book.getId() , book2.get().getId());


    }

    @Test
    public void book_purchase(){
        Book book = Book.builder()
                .bookName("testbook")
                .price(10000)
                .stock(Long.valueOf(100)).build();

        bookRepository.save(book);

        System.out.println(book.getStock());


        book.purchase(1);
        bookRepository.save(book);

        Optional<Book> book2 = bookRepository.findById(book.getId());

        //assertThat(book.getId()).isEqualTo(book2.get().getId());
        assertEquals(book.getStock() , book2.get().getStock());

    }

    @Test
    public void bookPurchase2_fail() throws InterruptedException {
        Book book = Book.builder()
                .bookName("testbook")
                .price(10000)
                .stock(Long.valueOf(100)).build();
        bookRepository.save(book);

        Long bookId = book.getId();

        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch countDownLatch = new CountDownLatch(100);

        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                try {
                    bookService.purchase(bookId, 1L);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        Book actual = bookRepository.findById(bookId)
                .orElseThrow();


        System.out.println(book.getStock());

        Long a = actual.getStock();
        assertThat(a).isZero();


    }


    @Test
    public void bookPurchase3_fail() throws InterruptedException {
        Book book = Book.builder()
                .bookName("testbook")
                .price(10000)
                .stock(Long.valueOf(100)).build();
        bookRepository.save(book);

        Long bookId = book.getId();

        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch countDownLatch = new CountDownLatch(100);

        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                try {
                    bookService.purchase2(bookId, 1L);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        Book actual = bookRepository.findById(bookId)
                .orElseThrow();


        System.out.println(book.getStock());

        Long a = actual.getStock();
        assertThat(a).isZero();


    }

    @Test
    public void bookPurchase3_success() throws InterruptedException {
        Book book = Book.builder()
                .bookName("testbook")
                .price(10000)
                .stock(Long.valueOf(100)).build();
        bookRepository.save(book);

        Long bookId = book.getId();

        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch countDownLatch = new CountDownLatch(100);

        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                try {
                    bookLockFacade.purchase(bookId, 1L);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        Book actual = bookRepository.findById(bookId)
                .orElseThrow();


        System.out.println(actual.getStock());

        Long a = actual.getStock();
        assertThat(a).isZero();


    }
}