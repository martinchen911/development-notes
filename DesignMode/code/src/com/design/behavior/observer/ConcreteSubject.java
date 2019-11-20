package com.design.behavior.observer;

/**
 * @Author chen
 * @Date 2019/11/19
 */
public class ConcreteSubject extends Subject {
    @Override
    public void notifyObserver() {
        System.out.println("具体目标发生改变...");
        System.out.println("--------------");

        for(Object obs:observers) {
            ((Observer)obs).response();
        }
    }
}
