package com.cf.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author chen
 * 2020/4/26
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取
     * @param ctx 上下文对象
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("ctx = " + ctx);
        ByteBuf bf = (ByteBuf) msg;
        System.out.println("msg = " + bf.toString(CharsetUtil.UTF_8));
        System.out.println("addr = " + ctx.channel().remoteAddress());

        // 异步执行1
        ctx.channel().eventLoop().execute(() -> {
            // 线程休眠
            try {
                TimeUnit.SECONDS.sleep(1);
                ctx.writeAndFlush(Unpooled.copiedBuffer("模拟服务端耗时任务1",CharsetUtil.UTF_8));
                System.out.println("ctx.channel().hashCode() = " + ctx.channel().hashCode());
            } catch (InterruptedException e) { e.printStackTrace(); }
        });

        // 异步执行定时任务
        ctx.channel().eventLoop().schedule(() -> {
            // 线程休眠
            try {
                ByteBuf buf = Unpooled.copiedBuffer("异步执行定时任务", CharsetUtil.UTF_8);
                ctx.writeAndFlush(buf);
                System.out.println("ctx.channel().hashCode() = " + ctx.channel().hashCode());
            } catch (Exception e) { e.printStackTrace(); }
        },5,TimeUnit.SECONDS);



    }

    /**
     * 读取完毕后操作
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello",CharsetUtil.UTF_8));
    }

    /**
     * 异常处理
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
    }
}
