package com.design.create.singleton;

/**
 * 饿汉式
 *  - 枚举：等价静态常量实现方式，一种语法糖
 * @Author chen
 * @Date 2019/11/23
 */
public class Hunger03 {
    public static void main(String[] args) {
        Singleton instance = Singleton.INSTANCE;
        System.out.println(instance);
    }

    enum Singleton {
        INSTANCE
    }
}
