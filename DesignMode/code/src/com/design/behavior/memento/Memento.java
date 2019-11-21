package com.design.behavior.memento;

/**
 * 备忘录
 * @Author chen
 * @Date 2019/11/21
 */
public class Memento {
    private String state;
    public Memento(String state)
    {
        this.state=state;
    }
    public void setState(String state)
    {
        this.state=state;
    }
    public String getState()
    {
        return state;
    }
}
