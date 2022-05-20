package com.bnyte.test;

/**
 * @author bnyte
 * @since 2022/5/20 3:22
 */
public class Thread1 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1111111");
        }
    }
}
