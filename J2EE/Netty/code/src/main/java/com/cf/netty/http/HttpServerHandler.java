package com.cf.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @author chen
 * 2020/4/27
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        // 判断消息类型
        if (msg instanceof HttpRequest) {
            System.out.println("ctx.pipeline().hashCode() = " + ctx.pipeline().hashCode());
            System.out.println("this.Handler.hashCode() = " + this.hashCode());


            HttpRequest req = (HttpRequest)msg;
            URI uri = new URI(req.uri());
            if ("/favicon.ico".equals(uri.getPath())) {
                System.out.println("请求为 ico 图标，操作结束");
                return;
            }

            // 响应客户端
            ByteBuf buf = Unpooled.copiedBuffer("请求响应。。。", CharsetUtil.UTF_8);

            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf);

            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain;charset=UTF-8");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,buf.readableBytes());


            ctx.writeAndFlush(response);
        }
    }
}
