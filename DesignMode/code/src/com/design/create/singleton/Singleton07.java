package com.design.create.singleton;

/**
 * 静态内部类
 *
 * @Author chen
 * @Date 2019/11/23
 */
public class Singleton07 {

    private Singleton07(){}

    private static class SingletonInstance {
        private static final Singleton07 INSTANCE = new Singleton07();
    }

    public static synchronized Singleton07 getInstance() {
        return SingletonInstance.INSTANCE;
    }

}
