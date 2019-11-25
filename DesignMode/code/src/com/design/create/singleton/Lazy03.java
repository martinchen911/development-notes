package com.design.create.singleton;

/**
 * 懒汉式
 *  - 同步代码块
 *
 * @Author chen
 * @Date 2019/11/23
 */
public class Lazy03 {

    private Lazy03() {}

    private static Lazy03 instance;

    public static Lazy03 getInstance() {
        synchronized (instance) {
            if (null == instance) {
                instance = new Lazy03();
            }
        }
        return instance;
    }

}
