package test60.api1;

import lombok.Data;

@Data
public class AbsApiResponse {

    private int code;
    private String message;
    private Object data;

    public AbsApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
