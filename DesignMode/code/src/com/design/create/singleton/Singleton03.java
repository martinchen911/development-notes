package com.design.create.singleton;

/**
 * 懒汉式
 *  - 异步方法
 *
 * @Author chen
 * @Date 2019/11/23
 */
public class Singleton03 {

    private Singleton03() {}

    private static Singleton03 instance;

    public static Singleton03 getInstance() {
        if (null == instance) {
            instance = new Singleton03();
        }
        return instance;
    }

}
