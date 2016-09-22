package test20.oozie;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

/**
 * Created by chin on 9/22/16.
 */
public abstract class AbstractClientCallable<T> implements Callable<T> {


    private String conf;

    protected AbstractClientCallable(String conf) {
        this.conf = conf;
    }

    public T call() throws CustomExcpetion {

        HttpURLConnection url = null;//from conf

        return call(url);
    }

    protected abstract T call(HttpURLConnection conn) throws CustomExcpetion ;
}
