package com.design.create.singleton;

/**
 * 懒汉式
 *  - 双重检查
 *
 * @Author chen
 * @Date 2019/11/23
 */
public class Lazy04 {

    private Lazy04() {}

    private static Lazy04 instance;

    public static Lazy04 getSingleton06() {
        // 为空则同步
        if (null == instance) {
            synchronized (instance) {
                if (null == instance) {
                    instance = new Lazy04();
                }
            }
        }
        return instance;
    }
}
