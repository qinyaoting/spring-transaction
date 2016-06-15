package threadlocal;

/**
 * Created by chin on 6/14/16.
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocalStub testThread1 = new ThreadLocalStub();
        ThreadLocalStub testThread2 = new ThreadLocalStub();
        ThreadLocalStub testThread3 = new ThreadLocalStub();
        testThread1.start();
        testThread2.start();
        testThread3.start();
    }
}
