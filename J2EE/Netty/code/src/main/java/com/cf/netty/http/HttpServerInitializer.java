package com.cf.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author chen
 * 2020/4/27
 */
public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel sc) throws Exception {
        ChannelPipeline pipeline = sc.pipeline();
        // 添加 http 编/解码器
        pipeline.addLast("serverCode",new HttpServerCodec());
        // 添加自定义处理器
        pipeline.addLast("myHandler",new HttpServerHandler());

    }
}
