package github.akanemiku.akaneblog.utils;

import com.vdurmont.emoji.EmojiParser;
import github.akanemiku.akaneblog.constant.WebConst;
import github.akanemiku.akaneblog.model.Log;
import github.akanemiku.akaneblog.model.Meta;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * 常用工具
 */
@Component
public class Commons {

    /**
     * 添加新日志
     * @param action
     * @param data
     * @param authorId
     * @param request
     * @return
     */
    public static Log newLog(String action, Object data, Integer authorId, HttpServletRequest request){
        return new Log(null,action,JsonUtil.toJsonString(data),authorId, IPUtils.getIpAddress(request),DateUtil.getCurrentUnixTime());
    }

    /**
     * 数组转字符串
     * @param arr 数组
     * @return String
     */
    public static String arrayToString(String[] arr) {
        StringBuffer buffer = new StringBuffer();
        String[] temp = arr;
        int length = arr.length;

        for (int i = 0; i < length; i++) {
            String item = temp[i];
            buffer.append(",").append(item);
        }
        return buffer.length() > 0 ? buffer.substring(1) : buffer.toString();
    }

    /**
     * 判断category和cat的交集
     * @param category
     * @param cats
     * @return
     */
    public static boolean exist_cat(Meta category, String cats) {
        String[] arr = StringUtils.split(cats, ",");
        if (null != arr && arr.length > 0) {
            for (String c : arr) {
                if (c.trim().equals(category.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 定义颜色样式数组
     */
    private static final String[] COLORS = {"default", "primary", "success", "info", "warning", "danger", "inverse", "purple", "pink"};

    /**
     * 随机样式
     * @return
     */
    public static String rand_color() {
        int r = RandomUtil.genNumIncludeMinAndMax(0, COLORS.length - 1);
        return COLORS[r];
    }

    /**
     * 带后缀生成指定范围的随机整数
     * @param num
     * @return
     */
    public static String random(int num,String suf) {
        return  RandomUtil.genNumIncludeMinAndMax(1,num)+suf;
    }


    public static String getIdenticons() {
        String avatarUrl = "https://github.com/identicons/";
        String hash = SpecialUtil.MD5encode(
                RandomUtil.getAlphanumericString(RandomUtil.genNum(0,10)));
        return avatarUrl + hash + ".png";
    }

    /**
     * 格式化unix时间戳为日期
     * thymeleaf中使用
     * @param unixTime
     * @param patten
     * @return
     */
    public static String fmtdate(Integer unixTime, String patten) {
        if (null != unixTime && StringUtils.isNotBlank(patten)) {
            return DateUtil.formatDateByUnixTime(unixTime,patten);
        }
        return "";
    }

    /**
     * 返回blog文章地址
     * @param cid
     * @return
     */
    public static String blogPermalink(Integer cid) {
        return site_url("/blog/article/" + cid.toString());
    }

    /**
     * 网站链接
     * @return
     */
    public static String site_url() {
        return site_url("");
    }

    /**
     * 返回网站链接下的全址
     * @param sub   后面追加的地址
     * @return
     */
    public static String site_url(String sub) {
        return site_option("site_url") + sub;
    }

    /**
     * 获取网站备案信息
     * @return
     */
    public static String site_record() {
        return site_option("site_record");
    }

    /**
     * 网站配置项
     * @param key
     * @return
     */
    public static String site_option(String key) {
        return site_option(key, "");
    }

    /**
     * 网站配置项
     * @param key           键名
     * @param defaultValue  默认值
     * @return
     */
    public static String site_option(String key, String defaultValue) {
        if (StringUtils.isBlank(key)) {
            return "";
        }
        String str = WebConst.initConfig.get(key);
        if (StringUtils.isNotBlank(str)) {
            return str;
        } else {
            return defaultValue;
        }
    }

    /**
     * 字符串截取
     * @param str   截取的字符串
     * @param len   截取的长度
     * @return      截取之后字符串
     */
    public static String subStr(String str, Integer len) {
        if (null == len) {
            len = 100;
        }
        String tempStr = null;
        if (str.length() > len) {
            tempStr = str.substring(0,len);
            return tempStr + "...";
        }
        return str;
    }

    /**
     * 截取字符串
     *
     * @param str
     * @param len
     * @return
     */
    public static String substr(String str, int len) {
        if (str.length() > len) {
            return str.substring(0, len);
        }
        return str;
    }

    /**
     * 显示文章内容，转换markdown为HTML
     * @param markdown
     * @return
     */
    public static String article(String markdown) {
        if (StringUtils.isNotBlank(markdown)) {
            markdown = markdown.replace("<!--more-->", "\r\n");
            markdown = markdown.replace("<!-- more -->", "\r\n");
            return SpecialUtil.markdownToHtml(markdown);
        }
        return "";
    }

    /**
     * An :grinning:awesome :smiley:string &#128516;with a few :wink:emojis!
     * <p>
     * 这种格式的字符转换为emoji表情
     *
     * @param value
     * @return
     */
    public static String emoji(String value) {
        return EmojiParser.parseToUnicode(value);
    }


    /**
     * 获取社交的链接地址
     * @return
     */
    public static Map<String, String> social() {
        final String prefix = "social_";
        Map<String, String> map = new HashMap<>();
        map.put("csdn", WebConst.initConfig.get(prefix + "csdn"));
        map.put("jianshu", WebConst.initConfig.get(prefix + "jianshu"));
        map.put("resume", WebConst.initConfig.get(prefix + "resume"));
        map.put("weibo", WebConst.initConfig.get(prefix + "weibo"));
        map.put("zhihu", WebConst.initConfig.get(prefix + "zhihu"));
        map.put("github", WebConst.initConfig.get(prefix + "github"));
        map.put("twitter", WebConst.initConfig.get(prefix + "twitter"));
        return map;
    }


}
