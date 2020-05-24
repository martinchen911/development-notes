package com.cf.netty.rpc;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chen
 * 2020/5/23
 */
public class NettyClient {

    private static ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private static NettyClientHandler client;

    private static void init() {
        client = new NettyClientHandler();
        NioEventLoopGroup work = new NioEventLoopGroup();
        try {
            Bootstrap bs = new Bootstrap();
            bs.group(work)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());
                            pipeline.addLast(client);
                        }
                    });
            bs.connect("127.0.0.1",8848).sync();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    public Object getInstance(final Class<?> serviceClass,final String protocolHeader) {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class<?>[]{serviceClass},
                (proxy,method,args) -> {
                    if (client == null) {
                        init();
                    }
                    client.setParam(protocolHeader + args[0]);
                    return pool.submit(client).get();
                });
    }

}
