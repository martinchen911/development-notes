package com.design.create.singleton;

/**
 * 懒汉式
 *  - 同步方法
 *
 * @Author chen
 * @Date 2019/11/23
 */
public class Singleton04 {

    private Singleton04() {}

    private static Singleton04 instance;

    public static synchronized Singleton04 getInstance() {
        if (null == instance) {
            instance = new Singleton04();
        }
        return instance;
    }

}
