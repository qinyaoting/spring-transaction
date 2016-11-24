package test37.socketserver;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by chin on 10/9/15.
 */
public class Client4 {

    public static void main(String args[]) throws Exception {

        for (int i = 0; i < 10; i++) {
            tp.execute(new EchoClient());
        }
        //tp.shutdown();


    }

    private static ExecutorService tp = Executors.newCachedThreadPool();



}


