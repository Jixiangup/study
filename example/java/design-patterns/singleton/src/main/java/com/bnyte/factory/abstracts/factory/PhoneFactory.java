package com.bnyte.factory.abstracts.factory;

import com.bnyte.factory.abstracts.Phone;
import com.bnyte.factory.abstracts.impl.IPhone;
import com.bnyte.factory.abstracts.impl.XiaoMi;

/**
 * @author bnyte
 * @since 2022/5/20 15:19
 */
public class PhoneFactory {

    public static Phone getInstance(Class<? extends Phone> iClass) {
        if (iClass.getName().equals(XiaoMi.class.getName())) {
            return new XiaoMi();
        }
        else if (iClass.getName().equals(IPhone.class.getName())) return new IPhone();
        return null;
    }

}
