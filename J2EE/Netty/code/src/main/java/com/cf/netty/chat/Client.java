package com.cf.netty.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.util.Scanner;

/**
 * 群聊客户端
 * @author chen
 * 2020/5/15
 */
public class Client {

    private String host;
    private int port;

    public Client(String host,int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws InterruptedException {
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            Bootstrap boot = new Bootstrap();
            boot.group(workGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            ChannelPipeline pipeline = sc.pipeline();

                            pipeline.addLast("decode",new StringDecoder());
                            pipeline.addLast("encode",new StringEncoder());
                            pipeline.addLast("sendMsg",new ClientHandler());
                        }
                    });

            ChannelFuture channelFuture = boot.connect(this.host, this.port).sync();
            Channel channel = channelFuture.channel();
            System.out.println("-------" + channel.localAddress()+ "--------");
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                channel.writeAndFlush(scanner.nextLine());
            }
        } finally {
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Client client = new Client("127.0.0.1",8848);
        client.run();
    }


}
