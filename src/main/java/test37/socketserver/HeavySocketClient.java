package test37.socketserver;

/**
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;


public class HeavySocketClient {


    private static ExecutorService tp = Executors.newCachedThreadPool();

    public static final int sleep_time = 2 * 1000;

    public static class EchoClient implements Runnable {
        @Override
        public void run() {

            Socket client = null;
            PrintWriter writer = null;
            BufferedReader reader = null;
            try {
                 client = new Socket();
                client.connect(new InetSocketAddress("localhost",8000));
                 writer = new PrintWriter(client.getOutputStream(),true);
                writer.print("H");
                LockSupport.parkNanos(sleep_time);
                writer.print("e");
                LockSupport.parkNanos(sleep_time);
                writer.print("l");
                LockSupport.parkNanos(sleep_time);
                writer.print("l");
                LockSupport.parkNanos(sleep_time);
                writer.print("o");
                LockSupport.parkNanos(sleep_time);
                writer.print("!");
                LockSupport.parkNanos(sleep_time);

                writer.println();
                writer.flush();
                reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                System.out.printf("from server:%s\n" , reader.readLine());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("finally...ed");
                try {
                    if (writer != null) writer.close();
                    if (reader != null) reader.close();
                    if (client != null) client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("finally...ed111");
            }
        }
    }

    public static void main(String[] args) {
        EchoClient ec = new EchoClient();
        for (int i = 0; i < 10; i++) {
            tp.execute(ec);
        }
        tp.shutdown();
    }


}
