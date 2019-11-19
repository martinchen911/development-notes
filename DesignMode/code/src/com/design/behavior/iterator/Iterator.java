package com.design.behavior.iterator;

/**
 * 抽象迭代器
 *
 * @Author chen
 * @Date 2019/11/19
 */
public interface Iterator {

    Object first();
    Object next();
    boolean hasNext();
}
