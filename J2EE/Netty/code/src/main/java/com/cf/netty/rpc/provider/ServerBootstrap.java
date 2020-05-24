package com.cf.netty.rpc.provider;

import com.cf.netty.rpc.NettyServer;

/**
 * 启动服务提供者
 * @author chen
 * 2020/5/23
 */
public class ServerBootstrap {


    public static void main(String[] args) {
        NettyServer.star0("127.0.0.1",8848);
    }

}
