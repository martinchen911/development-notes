package com.design.create.singleton;

/**
 * 饿汉式
 *  - 静态常量实现
 * @Author chen
 * @Date 2019/11/23
 */
public class Hunger01 {

    private Hunger01() {}

    public static final Hunger01 instance = new Hunger01();

}
