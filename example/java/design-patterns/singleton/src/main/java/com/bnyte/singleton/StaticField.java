package com.bnyte.singleton;

/**
 * 饿汉式 静态变量
 *  优点:
 *      1. 这种写法比较简单,就是在类加载的时候就完成了实例化.避免了线程同步的问题.
 *  缺点:
 *      1. 在类加载的时候完成实例化,没有达到lazy loading的效果.如果始终从未使用过这个示例,则会造成内存的浪费.
 *
 *  可以用,可能造成内存浪费
 *
 * @author bnyte
 * @since 2022/5/20 2:53
 */
public class StaticField {

    private static final StaticField instance = new StaticField();

    private StaticField() {

    }

    /**
     * 提供静态方法返回当前类对象
     *
     * @return 返回当前类实例对象
     */
    public static StaticField getInstance() {
        return instance;
    }
}
