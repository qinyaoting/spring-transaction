package test37.socketserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by chin on 10/9/15. !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */
public class MultiThreadEchoServer {


    public static void main(String[] args) throws IOException {
        ServerSocket echoServer = new ServerSocket(8000);
        while (true) {
            try {
                Socket client = echoServer.accept();
                System.out.printf("%s connected... \n", client.getRemoteSocketAddress());
                tp.execute(new HandleMsg(client));

            } catch (IOException e) {

            }

        }
    }

    private static ExecutorService tp = Executors.newCachedThreadPool();

    static class HandleMsg implements Runnable {

        Socket clientSocket;

        HandleMsg(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {

            BufferedReader reader = null;
            PrintWriter writer = null;
            try {
                reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                writer = new PrintWriter(clientSocket.getOutputStream(),true);

                String inputLine = null;
                long b = System.currentTimeMillis();
                while ((inputLine = reader.readLine()) != null) {
                    writer.print(inputLine);
                }
                //writer.println();
                //writer.flush();
                //System.out.println("11111"+inputLine);
                long e = System.currentTimeMillis();
                System.out.printf("spend:%s",(e-b));



            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (writer != null) writer.close();
                    if (reader!=null)reader.close();
                    if (clientSocket != null) clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
