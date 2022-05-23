package com.bnyte.factory.factory.method;

import com.bnyte.factory.Coffee;
import com.bnyte.factory.factory.method.impl.LatteCoffeeFactory;

/**
 * @author bnyte
 * @since 2022/5/23 14:34
 */
public class Main {
    public static void main(String[] args) {
        CoffeeFactory instance = CoffeeFactory.getInstance(LatteCoffeeFactory.class);
        Coffee coffee = instance.make();
        System.out.println(coffee.getName());
    }
}
