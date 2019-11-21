package com.design.behavior.interpreter;

/**
 * 抽象表达式类
 * @Author chen
 * @Date 2019/11/21
 */
public interface  AbstractExpression {
    public Object interpret(String info);    //解释方法
}
