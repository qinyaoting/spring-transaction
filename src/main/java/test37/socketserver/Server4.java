package test37.socketserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by chin on 10/9/15.
 */
public class Server4 {

    public static void main(String args[]) throws IOException {
        //为了简单起见，所有的异常信息都往外抛
        int port = 8899;
        //定义一个ServerSocket监听在端口8899上
        ServerSocket server = new ServerSocket(port);
        while (true) {
            //server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的
            Socket socket = server.accept();
            System.out.printf("%s connected... \n", socket.getRemoteSocketAddress());
            //每接收到一个Socket就建立一个新的线程来处理它
            new Thread(new Task(socket)).start();
        }
    }


}



