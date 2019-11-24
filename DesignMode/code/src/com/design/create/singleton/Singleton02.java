package com.design.create.singleton;

/**
 * 饿汉式
 *  - 静态代码块
 *
 * @Author chen
 * @Date 2019/11/23
 */
public class Singleton02 {

    private Singleton02() {}

    private static Singleton02 instance;

    static {
        instance = new Singleton02();
    }

    public static Singleton02 getInstance() {
        return instance;
    }

}
