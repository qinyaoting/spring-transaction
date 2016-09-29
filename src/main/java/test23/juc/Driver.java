package test23.juc;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by chin on 9/29/16.
 */
public class Driver {

    public static final int N = 5;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(N);      //注意N

        for (int i = 0; i < N; i++)
            new Thread(new Worker(startSignal, doneSignal)).start();

        doSomethingElse();
        startSignal.countDown();        //2 主线程通知,worker线程恢复运行
        //doSomethingElse();
        doneSignal.await();             //3 等待全部worker线程完活,主线程才可以往下走

        System.out.println("main resume...");   //5

    }

    private static void doSomethingElse() throws InterruptedException {
        int time =  new Random().nextInt(5000);
        System.out.println("doSomethingElse sleep:" + time);
        Thread.sleep(time);
    }
}

class Worker implements Runnable {

    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;

    Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }

    @Override
    public void run() {

        try {
            System.out.println("start");
            startSignal.await();            //1 worker线程阻塞在这里
            System.out.println(Thread.currentThread().getName()+":job doing...");
            doWork();
            System.out.println(Thread.currentThread().getName()+":job done...");
            doneSignal.countDown();         //4 各个worker线程完活,向主线程报告,主线程接着运行
            System.out.println("finished");
        } catch (InterruptedException e){}

    }

    private void doWork() throws InterruptedException {

        int time =  new Random().nextInt(5000);
        //System.out.println("doWork sleep:" + time);
        Thread.sleep(time);
    }
}
