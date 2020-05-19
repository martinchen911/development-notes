package com.cf.netty.heartbeat;

import com.cf.netty.chat.ServiceHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * 心跳检测服务
 * @author chen
 * 2020/5/18
 */
public class Server {

    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void run() throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup work = new NioEventLoopGroup();

        try {
            ServerBootstrap sb = new ServerBootstrap()
                    .group(boss,work)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            ChannelPipeline pipeline = sc.pipeline();
                        /*
                        心跳检测
                        IdleStateHandler
                            - readerIdleTime 读空闲时间
                            - writerIdleTime 写空闲时间
                            - allIdleTime 读写空闲时间
                            - unit 时间单位
                         */
                            pipeline.addLast(new IdleStateHandler(3,5,7,
                                    TimeUnit.SECONDS));
                            pipeline.addLast(new ServerHandler());

                        }
                    });

            ChannelFuture cf = sb.bind(port).sync();
            cf.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Server(8848).run();
    }


}
