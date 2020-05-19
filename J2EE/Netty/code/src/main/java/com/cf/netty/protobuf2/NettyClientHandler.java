package com.cf.netty.protobuf2;

import com.cf.netty.protobuf.StudentPOJO;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Random;

/**
 * @author chen
 * 2020/5/17
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 当通道就绪就会触发该方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ResultPOJO.MyResult.Builder builder = ResultPOJO.MyResult.newBuilder();
        int num = new Random().nextInt(3);
        if (num == 1) {
            ResultPOJO.Student stu = ResultPOJO.Student.newBuilder().setId(1).setName("smallWang").build();
            builder.setType(ResultPOJO.MyResult.Type.stuType)
                    .setStudent(stu);
        } else {
            ResultPOJO.Teacher tea = ResultPOJO.Teacher.newBuilder().setId(1).setName("宇文萱").setSubject("数学").build();
            builder.setType(ResultPOJO.MyResult.Type.teaType)
                    .setTeacher(tea);
        }
        ctx.writeAndFlush(builder.build());
    }

}
