package com.cf.netty.rpc;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

/**
 * @author chen
 * 2020/5/23
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {
    /**
     * 上下文
     */
    private ChannelHandlerContext context;
    /**
     * 返回结果
     */
    private String result;
    /**
     * 参数
     */
    private String param;


    @Override
    public synchronized Object call() throws Exception {
        context.writeAndFlush(param);
        wait();
        return result;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        context = ctx;
    }

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        result = msg.toString();
        System.out.println("result = " + result);
        notify();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    public void setParam(String param) {
        this.param = param;
    }
}
