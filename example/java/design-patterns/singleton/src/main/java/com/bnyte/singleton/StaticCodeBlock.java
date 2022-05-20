package com.bnyte.singleton;

/**
 * 静态代码块,饿汉式
 *
 * @author bnyte
 * @since 2022/5/20 3:00
 */
public class StaticCodeBlock {

    private static final StaticCodeBlock instance;

    static {
        instance = new StaticCodeBlock();
    }

    public static StaticCodeBlock getInstance() {
        return instance;
    }

    private StaticCodeBlock() {

    }

}
