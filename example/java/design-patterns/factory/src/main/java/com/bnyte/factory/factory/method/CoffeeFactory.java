package com.bnyte.factory.factory.method;

import com.bnyte.factory.Coffee;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 工厂方法模式
 * @author bnyte
 * @since 2022/5/23 14:14
 */
public abstract class CoffeeFactory {

    public abstract Coffee make();

    public static CoffeeFactory getInstance(Class<? extends CoffeeFactory> typeClass) {
        try {
            Constructor<? extends CoffeeFactory> constructor = typeClass.getConstructor();
            return constructor.newInstance();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

}
