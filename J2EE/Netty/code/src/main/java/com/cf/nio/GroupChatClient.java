package com.cf.nio;

import sun.rmi.runtime.NewThreadAction;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * 群聊客户端
 * @author
 */
public class GroupChatClient {

    private static final String IP = "127.0.0.1";
    private static final int PORT = 8848;
    private Selector selector;
    private SocketChannel sChannel;
    private String userName;

    public GroupChatClient() throws IOException {
        this(IP,PORT);
    }
    public GroupChatClient(String ip, int port) throws IOException {
        selector = Selector.open();
        sChannel = SocketChannel.open(new InetSocketAddress(ip,port));
        sChannel.configureBlocking(false);
        sChannel.register(selector, SelectionKey.OP_READ);
        userName = sChannel.getLocalAddress().toString();
        System.out.println(userName+" 连接成功！");
    }

    public void readInfo() throws IOException {
        while (selector.select() > 0) {
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey sk = it.next();
                if (sk.isReadable()) {
                    SocketChannel sc = (SocketChannel) sk.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    sc.read(buffer);
                    System.out.println(new String(buffer.array()).trim());
                }
            }
            it.remove();
        }
    }

    public void sendInfo(String msg) throws IOException {
        msg = userName + " => " +msg;
        sChannel.write(ByteBuffer.wrap(msg.getBytes()));
    }


    public static void main(String[] args) throws IOException {
        GroupChatClient client = new GroupChatClient();

        new Thread(() -> {
            try {
                client.readInfo();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String next = scanner.next();
            client.sendInfo(next);
        }

    }
}
