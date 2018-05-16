package test57.proxy;

/**
 * Created by chin on 11/25/16.
 */
public class Proxy extends AbstractSubject {


    RealSubject rs;

    @Override
    void request() {
        if (rs == null)
            rs = new RealSubject();
        rs.request();
    }
}
