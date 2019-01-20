package test60.api1;

import com.alibaba.fastjson.JSONObject;

public class Client {

    public static void main(String[] args) {

        AbsApiResponse response = new AbsApiResponse(0,"ok");
        System.out.println(JSONObject.toJSON(response));
        AbsApiResponse response2 = new AbsApiResponse(9,"user not found");
        System.out.println(JSONObject.toJSON(response2));

        MeetOperatorImpl impl = new MeetOperatorImpl();
        boolean isSucc = impl.stop("123");
        System.out.println(""+isSucc);
    }
}
