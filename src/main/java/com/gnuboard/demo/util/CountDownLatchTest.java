package com.gnuboard.demo.util;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CountDownLatchTest {

    public static void main(String args[]) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(100);

        CountDownLatch readyLatch = new CountDownLatch(5);
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch finishLatch = new CountDownLatch(5);
        List<Thread> workers = Stream
                .generate(() -> new Thread(new Worker(readyLatch,
                        startLatch, finishLatch)))
                .limit(5)
                .collect(Collectors.toList());

        System.out.println("Start multi threads (tid: "
                + Thread.currentThread().getId() + ")");

        workers.forEach(Thread::start);


        readyLatch.await();
        System.out.println("3");
        System.out.println("Waited for ready and started doing some work (tid: "
                + Thread.currentThread().getId() + ")");
        startLatch.countDown();

        finishLatch.await();
        System.out.println("Finished (tid: "
                + Thread.currentThread().getId() + ")");
    }

    public static class Worker implements Runnable {
        private CountDownLatch readyLatch;
        private CountDownLatch startLatch;
        private CountDownLatch finishLatch;

        public Worker(CountDownLatch readyLatch, CountDownLatch startLatch,
                      CountDownLatch finishLatch) {
            this.readyLatch = readyLatch;
            this.startLatch = startLatch;
            this.finishLatch = finishLatch;
        }

        @Override
        public void run() {
            readyLatch.countDown();
            System.out.println(1);
            try {
                System.out.println(1.5);
                startLatch.await();
                System.out.println(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Do something (tid: "
                    + Thread.currentThread().getId() + ")");
            finishLatch.countDown();
        }
    }

}
