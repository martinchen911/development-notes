package com.cf.netty.protobuf;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

/**
 * @author chen
 * 2020/5/17
 */
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(bossGroup,workGroup)        // 设置两个线程组
                    .channel(NioServerSocketChannel.class)    //  服务器的通道实现
                    .option(ChannelOption.SO_BACKLOG,128)   // 设置线程队列等待连接的个数
                    .childOption(ChannelOption.SO_KEEPALIVE,true)   // 设置保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            ChannelPipeline pipeline = sc.pipeline();
                            // protobuf 解码
                            pipeline.addLast(new ProtobufDecoder(StudentPOJO.Student.getDefaultInstance()));
                            pipeline.addLast(new NettyServerHandler());
                        }
                    });    // 设置 workGroup 对应 pipeline 的处理器
            //
            ChannelFuture cf = bootstrap.bind(8848).sync();

            cf.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }
}
