package com.design.behavior.vistor;

/**
 * 具体访问者
 *
 * @Author chen
 * @Date 2019/11/18
 */
public class ConcreteVisitorA implements Visitor {

    @Override
    public void visit(ConcreteElementA element) {
        System.out.println("具体访问者A访问-->"+element.operation());
    }

    @Override
    public void visit(ConcreteElementB element) {
        System.out.println("具体访问者A访问-->"+element.operation());
    }
}
