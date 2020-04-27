package com.cf.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * 群聊服务端
 * @author chen
 */
public class GroupChatServer {

    private ServerSocketChannel ssChannel;
    private Selector selector;
    public static final int PORT = 8848;

    public GroupChatServer() throws Exception {
        this(PORT);
    }
    public GroupChatServer(int port) throws Exception {
        ssChannel = ServerSocketChannel.open();
        //切换非阻塞模式
        ssChannel.configureBlocking(false);
        //绑定连接
        ssChannel.bind(new InetSocketAddress(port));
        //获取选择器
        selector = Selector.open();
        //将通道注册到选择器
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    /**
     * 监听
     * @throws Exception
     */
    public void listen() throws Exception {
        //轮询获取选择器上已经准备就绪的事件
        while (selector.select() > 0) {
            //获取当前选择器中所已注册的选择键
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                //获取准备就绪的事件
                SelectionKey sk = it.next();

                //判断具体是什么事件准备就绪
                if (sk.isAcceptable()) {
                    // 接收
                    SocketChannel sChannel = ssChannel.accept();
                    sChannel.configureBlocking(false);
                    //将通道注册到选择器
                    sChannel.register(selector, SelectionKey.OP_READ);
                    //提示
                    System.out.println(sChannel.getRemoteAddress() + " 上线 ");
                } else if (sk.isReadable()) {
                    //读就绪
                    this.read(sk);
                }
                //删除已处理的事件
                it.remove();
            }
        }
    }

    private void read(SelectionKey sk)  {
        SocketChannel sc = (SocketChannel) sk.channel();
        ByteBuffer buf = ByteBuffer.allocate(1024);

        //读取数据
        int len = 0;
        try {
            len = sc.read(buf);
            if (len > 0){
                String msg = new String(buf.array(), 0, len);
                sendToOtherClients(msg,sc);
                buf.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void sendToOtherClients(String msg,SocketChannel sc) throws IOException {
        for (SelectionKey key : selector.keys()) {
            Channel channel = key.channel();

            if (channel instanceof SocketChannel && channel != sc) {
                SocketChannel dest = (SocketChannel) channel;
                dest.write(ByteBuffer.wrap(msg.getBytes()));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        GroupChatServer server = new GroupChatServer();

        server.listen();
    }



}
