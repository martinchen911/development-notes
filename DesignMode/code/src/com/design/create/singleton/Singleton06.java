package com.design.create.singleton;

/**
 * 双重检查
 *
 * @Author chen
 * @Date 2019/11/23
 */
public class Singleton06 {

    private Singleton06() {}

    private static Singleton06 instance;

    public static Singleton06 getSingleton06() {
        if (null == instance) { // 为空则同步
            synchronized (instance) {
                if (null == instance) {
                    instance = new Singleton06();
                }
            }
        }
        return instance;
    }
}
