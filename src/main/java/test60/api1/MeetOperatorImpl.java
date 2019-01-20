package test60.api1;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

public class MeetOperatorImpl implements MeetOperator {



    // read url from conf file
    String url = "http://www.baidu.com";


    @Override
    public boolean publish(MeetData meet) {     //都是请求api，再解析结果，能使用模板吗

        try {
            HttpUtils.post(url,JSONObject.toJSONString(meet));
            return true;
        }catch (IOException e) {

        }
        return false;
    }

    @Override
    public boolean stop(String roomId) {
        try {
            MeetData meet = new MeetData();
            meet.setActionType(MeetDataConst.STOP);
            meet.setMeetingRoomID(roomId);
            String response = HttpUtils.post(url, JSONObject.toJSONString(meet));
            //response = "{\"code\":0,\"message\":\"ok\"}";
            // response = "{\"code\":9,\"message\":\"user not found\"}";
            AbsApiResponse obj =JSONObject.parseObject(response, AbsApiResponse.class);
            if (obj.getCode() == 0)
                return true;
        } catch (IOException e) {

        }
        return false;
    }
}
