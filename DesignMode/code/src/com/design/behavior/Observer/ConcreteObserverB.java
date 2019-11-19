package com.design.behavior.Observer;

import org.omg.CORBA.Object;

/**
 * 具体观察者B
 *
 * @Author chen
 * @Date 2019/11/19
 */
public class ConcreteObserverB implements Observer {
    @Override
    public void response() {
        System.out.println("具体观察者B作出反应！");
    }
}
