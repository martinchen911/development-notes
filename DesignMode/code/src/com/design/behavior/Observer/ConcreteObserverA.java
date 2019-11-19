package com.design.behavior.Observer;

/**
 * 具体观察者A
 *
 * @Author chen
 * @Date 2019/11/19
 */
public class ConcreteObserverA implements Observer {
    @Override
    public void response() {
        System.out.println("具体观察者A作出反应！");
    }
}
