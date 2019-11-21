package com.design.behavior.memento;

/**
 * 管理者
 * @Author chen
 * @Date 2019/11/21
 */
public class Caretaker {
    private Memento memento;
    public void setMemento(Memento m)
    {
        memento=m;
    }
    public Memento getMemento()
    {
        return memento;
    }
}
