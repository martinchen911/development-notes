package com.design.behavior.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体聚合
 * @Author chen
 * @Date 2019/11/19
 */
public class ConcreteAggregate implements Aggregate {

    private List<Object> list=new ArrayList<Object>();

    @Override
    public void add(Object obj) {
        list.add(obj);
    }

    @Override
    public void remove(Object obj) {
        list.remove(obj);
    }

    @Override
    public Iterator getIterator() {
        return(new ConcreteIterator(list));
    }
}
