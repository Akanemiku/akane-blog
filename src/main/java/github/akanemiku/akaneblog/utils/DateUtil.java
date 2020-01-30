package github.akanemiku.akaneblog.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateUtil {

    /**
     * 根据时间格式，格式化unix时间
     * @param unixTime
     * @param dateFormat
     * @return
     */
    public static String formatDateByUnixTime(long unixTime, String dateFormat) {
        return dateFormat(new Date(unixTime * 1000L), dateFormat);
    }

    public static String dateFormat(Date date, String dateFormat) {
        if (date != null) {
            SimpleDateFormat format = new SimpleDateFormat(dateFormat);
            return format.format(date);
        }
        return "";
    }

    /**
     * 获得当前unix时间
     * @return
     */
    public static int getCurrentUnixTime() {
        return getUnixTimeByDate(new Date());
    }

    public static int getUnixTimeByDate(Date date) {
        return (int)(date.getTime() / 1000L);
    }

}
