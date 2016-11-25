package test37.socketserver;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by chin on 11/24/16.
 */
public class EchoClient implements Runnable {

    public static final long sleep_time = 1000*1000*1000;

    @Override
    public void run() {

        Socket client = null;
        PrintWriter writer = null;
        BufferedReader reader = null;
        try {
            //为了简单起见，所有的异常都直接往外抛
            String host = "127.0.0.1";  //要连接的服务端IP地址
            int port = 8899;   //要连接的服务端对应的监听端口
            //与服务端建立连接
            client = new Socket(host, port);
            //建立连接后就可以往服务端写数据了
            writer = new PrintWriter(client.getOutputStream(),true);
            System.out.println("client start write.");
            writer.print("Hello Server.1");
            LockSupport.parkNanos(sleep_time);
            writer.print("Hello Server.2");
            LockSupport.parkNanos(sleep_time);
            writer.print("Hello Server.3");
            LockSupport.parkNanos(sleep_time);
            writer.print("Hello Server.4");
            LockSupport.parkNanos(sleep_time);
            writer.print("Hello Server.5");
            LockSupport.parkNanos(sleep_time);
            writer.print("Hello Server.6");
            LockSupport.parkNanos(sleep_time);
            writer.println();
            writer.flush();
            System.out.println("client flush.");
            //写完以后进行读操作
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println("from server: " + reader.readLine());
            /*br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String temp;
            int index;
            while ((temp=br.readLine()) != null) {
                if ((index = temp.indexOf("eof")) != -1) {
                    sb.append(temp.substring(0, index));
                    break;
                }
                sb.append(temp);
            }
            System.out.println("from server: " + sb);*/

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("client finally method.");
            try {
                if (writer != null) writer.close();
                if (reader != null)  reader.close();
                if (client != null) client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
