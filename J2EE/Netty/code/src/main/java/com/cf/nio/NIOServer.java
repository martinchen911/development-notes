package com.cf.nio;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 同步非阻塞模式
 *  服务端
 * @Author chen
 * @Date 2019/12/6
 */
public class NIOServer {

    public static void main(String[] args) throws Exception{
        //1.准备通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        //切换非阻塞模式
        ssChannel.configureBlocking(false);
        //绑定连接
        ssChannel.bind(new InetSocketAddress(8848));

        //2.获取选择器
        Selector selector = Selector.open();

        //3.将通道注册到选择器
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);


        //4.轮询获取选择器上已经准备就绪的事件
        while (selector.select() > 0) {
            //5.获取当前选择器中所已注册的选择键
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                //6.获取准备就绪的事件
                SelectionKey sk = it.next();

                //7.判断具体是什么事件准备就绪
                if (sk.isAcceptable()) {
                    // 接收
                    SocketChannel sChannel = ssChannel.accept();
                    sChannel.configureBlocking(false);
                    //将通道注册到选择器
                    sChannel.register(selector, SelectionKey.OP_READ);
                } else if (sk.isReadable()) {
                    //读就绪
                    SocketChannel sChannel = (SocketChannel) sk.channel();
                    ByteBuffer buf = ByteBuffer.allocate(1024);


                    //读取数据
                    int len = 0;
                    while ((len = sChannel.read(buf))> 0){
                        System.out.println(new String(buf.array(), 0, len));
                        buf.clear();
                    }
                }
                //8.删除已处理的事件
                it.remove();
            }
        }
    }

}
