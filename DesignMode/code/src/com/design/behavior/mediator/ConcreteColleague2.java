package com.design.behavior.mediator;

/**
 * 具体同事类
 * @Author chen
 * @Date 2019/11/20
 */
class ConcreteColleague2 extends Colleague {
    @Override
    public void receive() {
        System.out.println("具体同事类2收到请求。");
    }
    @Override
    public void send() {
        System.out.println("具体同事类2发出请求。");
        //请中介者转发
        mediator.relay(this);
    }
}
