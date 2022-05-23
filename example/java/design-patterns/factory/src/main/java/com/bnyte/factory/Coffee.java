package com.bnyte.factory;

/**
 * 拿铁、美式咖啡、卡布奇诺等均为咖啡家族的一种产品，咖啡则作为一种抽象概念
 * @author bnyte
 * @since 2022/5/23 12:35
 */
public interface Coffee {

    /**
     * 获取咖啡名称
     */
    public abstract String getName();

}
