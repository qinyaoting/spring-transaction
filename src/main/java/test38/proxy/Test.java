package test38.proxy;

/**
 * Created by chin on 11/25/16.
 */
public class Test {

    public static void main(String[] args) {


        //客户端调用的是Proxy,实际上是RealSubject处理的请求


        Proxy p = new Proxy();
        p.request();
    }
}
