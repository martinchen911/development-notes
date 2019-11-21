package com.design.behavior.memento;

/**
 * 发起人
 *
 * @Author chen
 * @Date 2019/11/21
 */
public class Originator {
    private String state;
    public void setState(String state)
    {
        this.state=state;
    }
    public String getState()
    {
        return state;
    }
    public Memento createMemento()
    {
        return new Memento(state);
    }
    public void restoreMemento(Memento m)
    {
        this.setState(m.getState());
    }
}
