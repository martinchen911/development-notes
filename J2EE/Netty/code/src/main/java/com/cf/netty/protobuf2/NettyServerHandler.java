package com.cf.netty.protobuf2;

import com.cf.netty.protobuf.StudentPOJO;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author chen
 * 2020/5/17
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<ResultPOJO.MyResult> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ResultPOJO.MyResult msg) throws Exception {
        System.out.println("msg = " + msg);

        if (msg.getType() == ResultPOJO.MyResult.Type.stuType) {
            System.out.println("msg.getStudent() = " + msg.getStudent());
            System.out.println("msg.getStudent().getName() = " + msg.getStudent().getName());
        } else if (msg.getType() == ResultPOJO.MyResult.Type.teaType) {
            System.out.println("msg.getTeacher() = " + msg.getTeacher());
            System.out.println("msg.getTeacher().getName() = " + msg.getTeacher().getName());
        }
    }
}
