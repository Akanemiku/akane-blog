package github.akanemiku.akaneblog.constant;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统常量
 */
@Component
public class WebConst {
    /**
     * 网站配置
     */
    public static Map<String, String> initConfig = new HashMap<>();

    /**
     * 用户登录session的key
     */
    public static String LOGIN_SESSION_KEY = "login_user";

    /**
     * cookie的key
     */
    public static final String USER_IN_COOKIE = "USER_ID";

    /**
     * 最大获取文章条数
     */
    public static final int MAX_POSTS = 10;

    /**
     * 文章标题最多可以输入的文字个数
     */
    public static final int MAX_TITLE_COUNT = 200;

    /**
     * 文章内容最多可以输入的文字个数
     */
    public static final int MAX_CONTENT_COUNT = 20000;

    /**
     * 两次评论间间隔(s)
     */
    public static final int COMMENT_INTERVAL = 30;

}
