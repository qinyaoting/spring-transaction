package test28.queue;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by wangjl on 2016/5/31.
 */
public class Queue {


    // 从队列里取出命令,多线程提交的线程池
    static ExecutorService threadPool = Executors.newFixedThreadPool(5);

    //队列
    static LinkedBlockingQueue<String[]> queue = new LinkedBlockingQueue<String[]>(100);



    public Queue() {
        start();
    }

    /**
     * 启动多个线程,从队列里取数据
     */
    public static void start() {
        for (int i=0;i<5;i++)
            threadPool.submit(new genScriptFileAndExec());
    }

    /**
     * 从队列中取出参数,生成shell并执行
     */
    static class genScriptFileAndExec extends Thread {

        @Override
        public void run(){
            while(true) {
                try {
                    String[] comm = queue.take();
                    System.out.println(comm.toString());
                    //Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println("genScriptFileAndExec:" + e.getMessage());
                }
            }
        }
    }

    public void addQueue(String[] str) {
        queue.add(str);
    }

}
