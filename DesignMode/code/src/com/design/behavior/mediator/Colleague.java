package com.design.behavior.mediator;

/**
 * 抽象同事类
 * @Author chen
 * @Date 2019/11/20
 */
public abstract class Colleague {
    protected Mediator mediator;
    public void setMedium(Mediator mediator)
    {
        this.mediator=mediator;
    }
    public abstract void receive();
    public abstract void send();
}
