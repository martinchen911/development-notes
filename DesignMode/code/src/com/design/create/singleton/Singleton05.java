package com.design.create.singleton;

/**
 * 懒汉式
 *  - 同步代码块
 *
 * @Author chen
 * @Date 2019/11/23
 */
public class Singleton05 {

    private Singleton05() {}

    private static Singleton05 instance;

    public static Singleton05 getInstance() {
        synchronized (instance) {
            if (null == instance) {
                instance = new Singleton05();
            }
        }
        return instance;
    }

}
