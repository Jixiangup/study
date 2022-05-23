package com.bnyte.factory.factory.simple;

import com.bnyte.factory.Coffee;
import com.bnyte.factory.extend.Latte;
import com.bnyte.factory.factory.simple.CoffeeFactory;

/**
 * @author bnyte
 * @since 2022/5/23 12:58
 */
public class Main {
    public static void main(String[] args) {
        Coffee instance = CoffeeFactory.createInstance(Latte.class);
        System.out.println(instance.getName());
    }
}
