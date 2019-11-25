package com.design.create.singleton;

/**
 * 懒汉式
 *  - 同步方法
 *
 * @Author chen
 * @Date 2019/11/23
 */
public class Lazy02 {

    private Lazy02() {}

    private static Lazy02 instance;

    public static synchronized Lazy02 getInstance() {
        if (null == instance) {
            instance = new Lazy02();
        }
        return instance;
    }

}
