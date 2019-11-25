package com.design.create.singleton;

/**
 * 懒汉式
 *  - 异步方法
 *
 * @Author chen
 * @Date 2019/11/23
 */
public class Lazy01 {

    private Lazy01() {}

    private static Lazy01 instance;

    public static Lazy01 getInstance() {
        if (null == instance) {
            instance = new Lazy01();
        }
        return instance;
    }

}
