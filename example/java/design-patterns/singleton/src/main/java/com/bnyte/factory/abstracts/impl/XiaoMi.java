package com.bnyte.factory.abstracts.impl;

import com.bnyte.factory.abstracts.Phone;

/**
 * @author bnyte
 * @since 2022/5/20 15:18
 */
public class XiaoMi implements Phone {
    @Override
    public void make() {
        System.out.println("this is xiao mi phone");
    }
}
