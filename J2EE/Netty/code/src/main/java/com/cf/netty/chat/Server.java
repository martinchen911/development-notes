package com.cf.netty.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 群聊服务端
 * @author chen
 * 2020/5/15
 */
public class Server {

    /**
     * 监听端口
     */
    private int port;

    public Server(int port){
        this.port = port;
    }

    public void run() throws InterruptedException {
        // 创建两个线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap sb = new ServerBootstrap();
            sb.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            ChannelPipeline pipeline = sc.pipeline();
                            // 添加编码
                            pipeline.addLast("decoder",new StringDecoder());
                            // 添加编码
                            pipeline.addLast("encode",new StringEncoder());
                            // 自定义编码
                            pipeline.addLast("forward",new ServiceHandler());
                        }
                    });

            ChannelFuture cf = sb.bind(port).sync();
            //监听关闭
            cf.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Server server = new Server(8848);
        server.run();
    }

}
