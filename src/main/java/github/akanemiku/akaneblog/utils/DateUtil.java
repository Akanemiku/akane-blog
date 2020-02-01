package github.akanemiku.akaneblog.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateUtil {

    /**
     * 根据时间格式，格式化unix时间
     *
     * @param unixTime
     * @param format
     * @return
     */
    public static String formatDateByUnixTime(long unixTime, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(new Date(unixTime * 1000L));
    }

    /**
     * 获得当前unix时间
     *
     * @return
     */
    public static int getCurrentUnixTime() {
        return (int) (System.currentTimeMillis() / 1000L);
    }

}
