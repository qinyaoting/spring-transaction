package test59.template;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by chin on 1/6/17.
 */
public abstract class AbstractClass {


    //定义了子类要实现的抽象方法
    public abstract void primitiveOperation1();
    public abstract void primitiveOperation2();

    // 已经把子类要调用的方法的顺序排好了,对外暴露的是templateMethod方法,
    // 可能是实现流程太复杂,分成几个小的片段,
    public void templateMethod() throws IOException {
        primitiveOperation1();;

        primitiveOperation2();
        System.out.println("templateMethod...");

        URL url = new URL("http://www.yahoo.com");
        URLConnection conn = url.openConnection();
        conn.getInputStream();
    }
}
