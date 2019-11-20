package com.design.behavior.mediator;

/**
 * 抽象中介者
 * @Author chen
 * @Date 2019/11/20
 */
abstract class Mediator {
    public abstract void register(Colleague colleague);
    public abstract void relay(Colleague cl); //转发
}
