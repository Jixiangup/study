package com.bnyte.singleton;

import java.util.Objects;

/**
 * 懒汉模式，线程不安全没有锁
 *  优点:
 *      1. 在获取当前类的实例的时候才会创建对象,释放资源
 *  缺点:
 *      1. 可能在线程A获取对象时没有示例对象的话则创建,但是B线程也过来获取此时可能造成A\B都会获得一个单独的对象实例
 *
 * @author bnyte
 * @since 2022/5/20 3:03
 */
public class LazyLoadingNotLock {

    private static LazyLoadingNotLock instance;

    private LazyLoadingNotLock() {

    }

    public static LazyLoadingNotLock getInstance() {
        if (Objects.isNull(instance)) instance = new LazyLoadingNotLock();
        return instance;
    }

}
