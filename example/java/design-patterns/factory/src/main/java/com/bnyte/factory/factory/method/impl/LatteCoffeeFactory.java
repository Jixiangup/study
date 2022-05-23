package com.bnyte.factory.factory.method.impl;

import com.bnyte.factory.Coffee;
import com.bnyte.factory.extend.Latte;
import com.bnyte.factory.factory.method.CoffeeFactory;

/**
 * @author bnyte
 * @since 2022/5/23 14:24
 */
public class LatteCoffeeFactory extends CoffeeFactory {

    @Override
    public Coffee make() {
        return new Latte();
    }
}
