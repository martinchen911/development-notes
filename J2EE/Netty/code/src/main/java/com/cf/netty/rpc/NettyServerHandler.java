package com.cf.netty.rpc;

import com.cf.netty.rpc.consumer.ClientBootstrap;
import com.cf.netty.rpc.provider.HelloServiceImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author chen
 * 2020/5/23
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 获取消息，调用服务
        System.out.println("msg = " + msg);
        if(msg.toString().startsWith(ClientBootstrap.protocolHeader)) {

            String result = new HelloServiceImpl().sayHello(msg.toString().substring(msg.toString().lastIndexOf("#") + 1));
            ctx.writeAndFlush(result);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
