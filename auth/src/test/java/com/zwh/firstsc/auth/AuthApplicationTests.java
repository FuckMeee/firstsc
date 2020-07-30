package com.zwh.firstsc.auth;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
class AuthApplicationTests {

    @Test
    void contextLoads() throws InterruptedException {
        System.out.println("******************************************");

        NotSafeCounter counter = new NotSafeCounter();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(counter);
            thread.start();
        }
        Thread.sleep(2000);
        System.out.println("理论结果：" + 1000000 * 10);
        System.out.println("实际结果：" + counter.amount);
        System.out.println("差距是：" + (1000000 * 10 - counter.amount.get()));
    }

    static class NotSafeCounter implements Runnable {
        public AtomicInteger amount = new AtomicInteger(0);

        public void increase() {
            // amount++;
            amount.incrementAndGet();
        }

        @Override
        /*synchronized*/ public void run() {
            int turn = 0;
            // synchronized (this) {
                while (turn < 1000000) {
                    ++turn;
                    increase();
                }
            // }
        }
    }
}
