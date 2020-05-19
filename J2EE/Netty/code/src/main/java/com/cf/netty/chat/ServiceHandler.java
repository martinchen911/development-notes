package com.cf.netty.chat;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 服务端自定义处理
 * @author chen
 * 2020/5/15
 */
public class ServiceHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 管理所有连接的channel
     */
    private static ChannelGroup cGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);



    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        cGroup.add(ctx.channel());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println(ctx.channel().remoteAddress() + "上线了！");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "离线了！");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        cGroup.forEach(ch -> {
            if (ctx.channel() != ch) {
                ch.writeAndFlush(Unpooled.copiedBuffer(ctx.channel().remoteAddress()+"："+s, CharsetUtil.UTF_8));
            } else {
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
