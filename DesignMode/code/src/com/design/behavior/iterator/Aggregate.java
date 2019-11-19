package com.design.behavior.iterator;

/**
 * 抽象聚合
 *
 * @Author chen
 * @Date 2019/11/19
 */
public interface Aggregate<E> {
    void add(Object obj);
    void remove(Object obj);
    Iterator getIterator();
}
