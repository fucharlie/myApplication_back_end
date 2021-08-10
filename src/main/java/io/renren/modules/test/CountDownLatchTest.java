package io.renren.modules.test;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public static CountDownLatch countDownLatch1 = new CountDownLatch(1);
    public static CountDownLatch countDownLatch2 = new CountDownLatch(1);
    public static void main(String[] args) {
        Thread thread1 = new Thread(){
            @Override
            public void run(){
                System.out.println("A");
                countDownLatch1.countDown();
            }
        };
        Thread thread2 = new Thread(){
            @Override
            public void run(){
                try {
                    countDownLatch1.await();
                    System.out.println("B");
                    countDownLatch2.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread3 = new Thread(){
            @Override
            public void run(){
                try {
                    countDownLatch2.await();
                    System.out.println("C");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread3.start();
        thread2.start();
        thread1.start();

    }
}
