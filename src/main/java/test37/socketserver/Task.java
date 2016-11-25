package test37.socketserver;

import java.io.*;
import java.net.Socket;

/**
 * Created by chin on 11/24/16.
 */
public /**
 * 用来处理Socket请求的
 */
class Task implements Runnable {

    private Socket socket;

    public Task(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            handleSocket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 跟客户端Socket进行通信
     * @throws Exception
     */
    private void handleSocket() throws Exception {

        BufferedReader is = null;
        PrintWriter os = null;

        try {
            is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            os = new PrintWriter(socket.getOutputStream(), true);
            String inputLine = null;
            long b = System.currentTimeMillis();
            while ((inputLine = is.readLine()) != null) {
                System.out.println("=====server received msg:" + inputLine);
                os.println(inputLine);
            }
            long e = System.currentTimeMillis();
            System.out.println("=====spend" + (e-b)+"ms");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
                if (os != null) os.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }





        /*BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String temp = null;
        int index;
        long b = System.currentTimeMillis();
        String line = br.readLine();
        System.out.println(line);
        while ((temp=line) != null) {
            //System.out.println(temp);
            if ((index = temp.indexOf("eof")) != -1) {//遇到eof时就结束接收
                sb.append(temp.substring(0, index));
                break;
            }
            sb.append(temp);
        }
        //System.out.println("from client: " + sb);
        *//*Reader reader = new InputStreamReader(socket.getInputStream());
        char chars[] = new char[64];
        int len;
        StringBuilder sb = new StringBuilder();
        String temp;
        int index;
        while ((len=reader.read(chars)) != -1) {
            temp = new String(chars, 0, len);
            if ((index = temp.indexOf("eof")) != -1) {//遇到eof时就结束接收
                sb.append(temp.substring(0, index));
                break;
            }
            sb.append(temp);
        }*//*
        long e = System.currentTimeMillis();
        System.out.printf("spend:%s\n",(e-b));

        //读完后写一句
        Writer writer = new OutputStreamWriter(socket.getOutputStream());
        writer.write("Hello Client.");
        writer.write("eof\n");
        writer.flush();
        writer.close();
        br.close();
        socket.close();*/
    }
}
