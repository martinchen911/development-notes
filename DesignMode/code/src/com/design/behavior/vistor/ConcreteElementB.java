package com.design.behavior.vistor;

/**
 * 具体元素
 * @Author chen
 * @Date 2019/11/18
 */
public class ConcreteElementB implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String operation() {
        return "具体元素 B 的操作";
    }
}
