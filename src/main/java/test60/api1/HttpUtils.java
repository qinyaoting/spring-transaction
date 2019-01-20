package test60.api1;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: chin
 * Date: 7/3/18
 * Time: 1:37 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class HttpUtils {

    protected static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static String post(String url, String jsonBody) throws IOException {

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, jsonBody);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        logger.error("url:"+url + "body" + jsonBody);
        long cur = System.currentTimeMillis();
        Response response = client.newCall(request).execute();
        logger.error("url:"+url+"=spend:"+(System.currentTimeMillis()-cur)/1000);
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            String xx = response.body().string();
            logger.warn("error:url:"+url + "body" + body + "response:"+xx);
            throw new IOException(xx);
        }
    }
}
