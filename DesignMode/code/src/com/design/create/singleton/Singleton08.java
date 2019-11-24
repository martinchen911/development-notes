package com.design.create.singleton;

/**
 * 枚举
 * @Author chen
 * @Date 2019/11/23
 */
public class Singleton08 {
    public static void main(String[] args) {
        Singleton instance = Singleton.INSTANCE;
        System.out.println(instance);
    }

    enum Singleton {
        INSTANCE;
    }
}
