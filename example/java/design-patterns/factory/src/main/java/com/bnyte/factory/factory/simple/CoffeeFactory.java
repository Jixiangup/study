package com.bnyte.factory.factory.simple;

import com.bnyte.factory.Coffee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 简单咖啡工厂-用于创建不同类型的咖啡示例
 * @author bnyte
 * @since 2022/5/23 12:39
 */
public class CoffeeFactory {

    private static final Logger log = LoggerFactory.getLogger(CoffeeFactory.class);
    private static final Class<?> coffee = Coffee.class;

    public static Coffee createInstance(Class<? extends Coffee> typeClass) {
        List<Class<?>> interfaces = Stream.of(typeClass.getInterfaces()).collect(Collectors.toList());
        if (!interfaces.contains(coffee)) {
            throw new ClassCastException(String.format("Input type %s does not support conversion to %s", typeClass.getName(), coffee.getName()));
        }
        try {
            Constructor<? extends Coffee> constructor = typeClass.getConstructor();
            return constructor.newInstance();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
        // Type genericSuperclass = typeClass.getGenericSuperclass();
        // 获取泛型
        // ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        // System.out.println(parameterizedType.getActualTypeArguments());
        // log.info("generic super class type name for {}", genericSuperclass.getTypeName());
    }

    public static boolean hasElement(Collection<?> collection, Object object) {
        boolean hasElement = false;
        for (Object o : collection) {
            if (o.equals(object)) return true;
            hasElement = o.equals(object);
        }
        return hasElement;
    }

}
