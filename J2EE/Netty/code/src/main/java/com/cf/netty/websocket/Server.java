package com.cf.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.util.concurrent.TimeUnit;

/**
 * websocket 通讯实例
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
                            // http 编解码
                            pipeline.addLast(new HttpServerCodec());
                            // 以块方式写
                            pipeline.addLast(new ChunkedWriteHandler());
                            // 聚合多段 http 消息
                            pipeline.addLast(new HttpObjectAggregator(8192));
                            // 协议升级（http 升级 websocket）
                            pipeline.addLast(new WebSocketServerProtocolHandler("/ttt"));

                            //
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
