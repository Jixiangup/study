package com.bnyte;

import com.bnyte.singleton.LazyLoadingNotLock;
import com.bnyte.singleton.StaticCodeBlock;
import com.bnyte.singleton.StaticField;
import com.bnyte.test.Thread1;
import com.bnyte.test.Thread2;

/**
 * @author bnyte
 * @since 2022/5/20 2:56
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(staticField());
        System.out.println(staticCodeBlock());
        // https://hudaye-china.blog.csdn.net/?type=blog
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();

        thread1.run();
        thread2.run();

    }

    public static StaticField staticField() {
        return StaticField.getInstance();
    }

    public static StaticCodeBlock staticCodeBlock() {
        return StaticCodeBlock.getInstance();
    }

    public static LazyLoadingNotLock lazyLoadingNotLock() {
        return LazyLoadingNotLock.getInstance();
    }

    public static void testLazyLoadingNotLockError() {
        ((Runnable) () -> {
            System.out.println(lazyLoadingNotLock() + " -> " + "current thread name '" + Thread.currentThread().getName() + "'");
        }).run();
        ((Runnable) () -> {
            System.out.println(lazyLoadingNotLock() + " -> " + "current thread name '" + Thread.currentThread().getName() + "'");
        }).run();
    }

}
