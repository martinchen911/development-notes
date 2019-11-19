package com.design.behavior.vistor;

/**
 * 抽象访问者
 *
 * @Author chen
 * @Date 2019/11/18
 */
public interface Visitor {

    /**
     * 访问操作
     */
    void visit(ConcreteElementA elementA);
    /**
     * 访问操作
     */
    void visit(ConcreteElementB elementB);
}
