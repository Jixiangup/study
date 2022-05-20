package com.bnyte.test;

/**
 * @author bnyte
 * @since 2022/5/20 3:22
 */
public class Thread2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("2222222");
        }
    }
}
