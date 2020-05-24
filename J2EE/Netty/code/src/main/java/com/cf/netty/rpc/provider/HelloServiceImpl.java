package com.cf.netty.rpc.provider;

import com.cf.netty.rpc.inf.HelloService;

/**
 * 公共接口实现
 * @author chen
 * 2020/5/23
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "接收消息 （包括消息头）" + name;
    }
}
