package com.design.behavior.mediator;

/**
 * 具体同事类
 * @Author chen
 * @Date 2019/11/20
 */
class ConcreteColleague1 extends Colleague {
    @Override
    public void receive() {
        System.out.println("具体同事类1收到请求。");
    }
    @Override
    public void send() {
        System.out.println("具体同事类1发出请求。");
        //请中介者转发
        mediator.relay(this);
    }
}
