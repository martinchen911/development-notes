package com.design.create.singleton;

/**
 * 饿汉式
 *  - 静态代码块：适合复杂的创建过程
 *
 * @Author chen
 * @Date 2019/11/23
 */
public class Hunger02 {

    private Hunger02() {}

    private static Hunger02 instance;

    static {
        instance = new Hunger02();
    }

    public static Hunger02 getInstance() {
        return instance;
    }

}
