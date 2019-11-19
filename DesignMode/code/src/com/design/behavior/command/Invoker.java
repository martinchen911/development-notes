package com.design.behavior.command;

/**
 * 调用者
 *
 * @Author chen
 * @Date 2019/11/15
 */
public class Invoker {

    private Command command;

    public Invoker setCommand(Command command) {
        this.command = command;
        return this;
    }

    public Invoker(Command command) {
        this.command = command;
    }

    public void cell() {
        System.out.println("调用者执行命令cell()方法！");
        command.execute();
    }

}
