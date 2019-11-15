package com.design.behavior.command;

/**
 * 具体命令
 *
 * @Author LENOVO
 * @Date 2019/11/15
 */
public class ConcreteCommand implements Command {

    private Receiver receiver;

    public ConcreteCommand() {
        receiver = new Receiver();
    }

    @Override
    public void execute() {
        receiver.action();
    }

}
