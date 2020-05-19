package com.cf.netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleStateEvent;

import java.time.LocalDateTime;

/**
 * @author chen
 * 2020/5/18
 */
public class ServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接，handlerAdded()被调用 ");
        System.out.println("ctx.channel().id().asLongText() = " + ctx.channel().id().asLongText());
        System.out.println("ctx.channel().id().asShortText() = " + ctx.channel().id().asShortText());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("接收到的消息：" + msg.text());

        // 回复
        ctx.channel().writeAndFlush(new TextWebSocketFrame(LocalDateTime.now() + msg.text()));

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端断开连接，handlerRemoved()被调用 ");
        System.out.println("ctx.channel().id().asLongText() = " + ctx.channel().id().asLongText());
        System.out.println("ctx.channel().id().asShortText() = " + ctx.channel().id().asShortText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("com.cf.netty.websocket.ServerHandler.exceptionCaught => " + cause.getCause());
        ctx.close();
    }
}
