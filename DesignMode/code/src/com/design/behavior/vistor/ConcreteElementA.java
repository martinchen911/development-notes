package com.design.behavior.vistor;

/**
 * 具体元素
 * @Author chen
 * @Date 2019/11/18
 */
public class ConcreteElementA implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String operation() {
        return "具体元素 A 的操作";
    }
}
