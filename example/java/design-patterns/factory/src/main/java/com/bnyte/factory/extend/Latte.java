package com.bnyte.factory.extend;

import com.bnyte.factory.Coffee;

/**
 * 拿铁
 * @author bnyte
 * @since 2022/5/23 12:37
 */
public class Latte implements Coffee {

    @Override
    public String getName() {
        return "拿铁";
    }

}
