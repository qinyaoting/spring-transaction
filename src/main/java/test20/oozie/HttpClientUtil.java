package test20.oozie;

import java.net.HttpURLConnection;

/**
 * Created by chin on 9/22/16.
 */
public class HttpClientUtil extends AbstractClientCallable<String> {


    protected HttpClientUtil(String conf, String typeName) {
        super(conf, typeName);
    }

    @Override
    protected String call(HttpURLConnection conn) throws CustomExcpetion {

        //拿到连接,发送参数,获得返回结果

        return null;
    }
}
