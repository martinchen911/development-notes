package com.design.behavior.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象目标
 * @Author chen
 * @Date 2019/11/19
 */
public abstract class Subject {

    protected List<Observer> observers=new ArrayList<Observer>();

    /**
     * 增加观察者方法
     * @param observer
     */
    public void add(Observer observer) {
        observers.add(observer);
    }

    /**
     * 删除观察者方法
     * @param observer
     */
    public void remove(Observer observer) {
        observers.remove(observer);
    }

    /**
     * 通知观察者方法
     */
    public abstract void notifyObserver();

}
