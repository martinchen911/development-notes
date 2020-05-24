package com.cf.netty.rpc.consumer;

import com.cf.netty.rpc.NettyClient;
import com.cf.netty.rpc.inf.HelloService;

/**
 * @author chen
 * 2020/5/23
 */
public class ClientBootstrap {

    public static final String protocolHeader = "helloService#hello#";

    public static void main(String[] args) {
        NettyClient custom = new NettyClient();

        HelloService service = (HelloService) custom.getInstance(HelloService.class, protocolHeader);
        String res = service.sayHello("rpc hello");
        System.out.println("res = " + res);

    }
}
