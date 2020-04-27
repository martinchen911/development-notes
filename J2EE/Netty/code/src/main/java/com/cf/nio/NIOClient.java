package com.cf.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;

/**
 * 客户端
 */
public class NIOClient {

    public static void main(String[] args) throws Exception{
        //1.获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",8848));

        //2.切换为非阻塞式
        sChannel.configureBlocking(false);

        //3.准备发送数据
        String str = "nio 发送数据测试" + LocalDateTime.now();
        ByteBuffer buf = ByteBuffer.wrap(str.getBytes());

        //4.发送数据给服务端
        sChannel.write(buf);
        buf.clear();

        sChannel.close();
    }
}
