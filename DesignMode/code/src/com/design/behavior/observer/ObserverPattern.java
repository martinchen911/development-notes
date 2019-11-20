package com.design.behavior.observer;

/**
 * 观察者模式
 *
 * @Author chen
 * @Date 2019/11/19
 */
public class ObserverPattern {
    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();
        Observer obs1 = new ConcreteObserverA();
        Observer obs2 = new ConcreteObserverB();
        subject.add(obs1);
        subject.add(obs2);
        subject.notifyObserver();
    }
}
