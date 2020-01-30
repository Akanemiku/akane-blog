package github.akanemiku.akaneblog.utils;

import com.alibaba.fastjson.JSON;

/**
 * json转换工具
 */
public class JsonUtil {

    public static String toJsonString(Object object) {
        return object == null ? null : JSON.toJSONString(object) ;
    }
}
