package github.akanemiku.akaneblog.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class APIResponse<T> {

    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";

    private String code;
    private T data;
    private String msg;

    public APIResponse(String code) {
        this.code = code;
    }

    public APIResponse(String code, T data) {
        this.code = code;
        this.data = data;
    }

    public APIResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static APIResponse success() {
        return new APIResponse(SUCCESS);
    }

    public static APIResponse success(Object data) {
        return new APIResponse(SUCCESS, data);
    }

    public static APIResponse failure(String msg) {
        return new APIResponse(FAILURE,msg);
    }
}
