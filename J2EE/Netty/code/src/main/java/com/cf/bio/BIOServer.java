package com.cf.bio;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * bio 同步阻塞网络模型
 *  -- 客户端 telnet 替代
 *
 * @Author chen
 * @Date 2019/12/6
 */
public class BIOServer {

    public static void main(String[] args) throws IOException {
        //思路
        //1. 创建一个线程池
        //2. 如果有客户端连接，就创建一个线程，与之通讯(单独写一个方法)
        ExecutorService threadPool = Executors.newCachedThreadPool();

        // 创建ServerSocket
        ServerSocket sSocket = new ServerSocket(9999);

        System.out.println("服务器启动...");

        while (true) {
            final Socket socket = sSocket.accept();
            threadPool.execute(() ->handler(socket));
        }

    }

    public static void handler(Socket socket) {
        byte[] bytes = new byte[1024];
        // 通过 socket 获取输入流
        try{
            InputStream inputStream = socket.getInputStream();
            // 循环读取
            for (;;) {
                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes,0,read));
                } else break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
