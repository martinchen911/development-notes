package com.design.behavior.command;

import com.design.behavior.command.Command;
import com.design.behavior.command.ConcreteCommand;
import com.design.behavior.command.Invoker;

/**
 * 命令模式
 *
 * @Author CHenFan
 * @Date 2019/11/15
 */
public class CommandPattern {
    public static void main(String[] args) {
        Command command = new ConcreteCommand();
        Invoker invoker = new Invoker(command);
        invoker.cell();

    }
}
