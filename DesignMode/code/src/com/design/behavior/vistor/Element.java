package com.design.behavior.vistor;

/**
 * 抽象元素
 *
 * @Author chen
 * @Date 2019/11/18
 */
public interface Element {

    void accept(Visitor visitor);
}
