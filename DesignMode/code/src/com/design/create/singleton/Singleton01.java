package com.design.create.singleton;

/**
 * 饿汉式
 *  - 静态常量实现
 * @Author chen
 * @Date 2019/11/23
 */
public class Singleton01 {

    private Singleton01() {}

    private final static Singleton01 instance = new Singleton01();

    public final static Singleton01 getInstance(){
        return instance;
    }

}
