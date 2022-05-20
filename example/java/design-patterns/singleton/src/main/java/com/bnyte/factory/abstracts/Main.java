package com.bnyte.factory.abstracts;

import com.bnyte.factory.abstracts.factory.PhoneFactory;
import com.bnyte.factory.abstracts.impl.XiaoMi;

/**
 * @author bnyte
 * @since 2022/5/20 15:22
 */
public class Main {
    public static void main(String[] args) {
        Phone instance = PhoneFactory.getInstance(XiaoMi.class);
        instance.make();
    }
}
