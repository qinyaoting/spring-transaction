package test57.proxy;

/**
 * Created by chin on 11/25/16.
 */
public class RealSubject extends AbstractSubject {
    @Override
    void request() {
        System.out.println("处理请求.......");
    }
}
