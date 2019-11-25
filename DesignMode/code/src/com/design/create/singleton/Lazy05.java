package com.design.create.singleton;

/**
 * 懒汉式
 *  - 静态内部类
 *
 * @Author chen
 * @Date 2019/11/23
 */
public class Lazy05 {

    private Lazy05(){}

    private static class SingletonInstance {
        private static final Lazy05 INSTANCE = new Lazy05();
    }

    public static synchronized Lazy05 getInstance() {
        return SingletonInstance.INSTANCE;
    }

}
