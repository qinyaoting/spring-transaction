package test5.threadlocal;

/**
 * Created by chin on 6/14/16.
 */
public class ThreadLocalStub extends Thread {

    public void run() {
        for (int i = 0; i < 2; i++) {
            LocalCounter localCounter = LocalCounter.getCounter();

            System.out.println("Thread[" + Thread.currentThread().getName()  + "],localCounter=" + localCounter.number++);
            System.out.println("Thread[" + Thread.currentThread().getName()   + "],Counter=" + Counter.number++);

            LocalCounter.setCounter(localCounter);
        }
        nextAdd();
    }

    private void nextAdd() {
        LocalCounter localCounter = LocalCounter.getCounter();
        System.out.println("Thread[" + Thread.currentThread().getName()   + "],localCounter=" + localCounter.number++);
        System.out.println("Thread[" + Thread.currentThread().getName()  + "],Counter=" + Counter.number++);

        LocalCounter.setCounter(localCounter);
    }
}
