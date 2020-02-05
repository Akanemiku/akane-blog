package github.akanemiku.akaneblog.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomUtil {
    /**
     * 生成指定区间随机数 (min, max)
     *
     * @param min 区间最小值(不包含)
     * @param max 区间最大值(不包含)
     * @return
     */
    public static int genNum(int min, int max) {
        if (min >= max - 1) {
            return 0;
        }
        Random random = new Random();
        return random.nextInt(max - min - 1) + min + 1;
    }

    /**
     * 生成指定区间随机数 [min, max)
     *
     * @param min 区间最小值(包含)
     * @param max 区间最大值(不包含)
     * @return
     */
    public static int genNumIncludeMin(int min, int max) {
        if (min >= max) {
            return 0;
        }
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    /**
     * 生成指定区间随机数 (min, max]
     *
     * @param min 区间最小值(不包含)
     * @param max 区间最大值(包含)
     * @return
     */
    public static int genNumIncludeMax(int min, int max) {
        return genNumIncludeMin(min, max) + 1;
    }

    /**
     * 生成指定区间随机数 [min, max]
     *
     * @param min 区间最小值(包含)
     * @param max 区间最大值(包含)
     * @return
     */
    public static int genNumIncludeMinAndMax(int min, int max) {
        if (min >= max + 1) {
            return 0;
        }
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * 生成指定长度随机数
     *
     * @param len 指定长度
     * @return
     */
    public static int genNumByLen(int len) {
        if (len < 1 || len > 9) {
            return 0;
        }
        return Integer.valueOf(genNumStrByLen(len));
    }

    /**
     * 生成指定长度随机数
     *
     * @param len 指定长度
     * @return
     */
    public static String genNumStrByLen(int len) {
        if (len < 1) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (i == 0) {
                sb.append(genNumIncludeMax(0, 9));
            } else {
                sb.append(genNumIncludeMinAndMax(0, 9));
            }
        }
        return sb.toString();
    }

    /**
     * 创建一个随机字符串
     *
     * @param length  长度
     * @param letters 生成的字符串可以包括字母字符
     * @param numbers 生成的字符串可以包含数字字符
     * @return
     */
    public static String getString(int length, boolean letters, boolean numbers) {
        return RandomStringUtils.random(length, letters, numbers);
    }

    /**
     * 创建一个随机字符串,将从所有字符集中选择字符
     *
     * @param length 长度
     * @return
     */
    public static String getString(int length) {
        return RandomStringUtils.random(length);
    }

    /**
     * 创建一个随机字符串，其长度是指定的字符数
     *
     * @param length 长度
     * @param chars  指定字符内容
     * @return
     */
    public static String getString(int length, String chars) {
        return RandomStringUtils.random(length, chars);
    }

    /**
     * 产生一个长度为指定的随机字符串的字符数，字符将从拉丁字母（a-z、A-Z）
     *
     * @param length 长度
     * @return
     */
    public static String getAlphabetString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    /**
     * 创建一个随机字符串，其长度介于包含最小值和最大最大值之间,，字符将从拉丁字母（a-z、A-Z）
     *
     * @param min 最小长度
     * @param max 最大长度
     * @return
     */
    public static String getAlphabetString(int min, int max) {
        return RandomStringUtils.randomAlphabetic(min, max);
    }

    /**
     * 产生一个长度为指定的随机字符串的字符数，字符将从拉丁字母（a-z、A-Z）和数字0-9中选择
     *
     * @param length 长度
     * @return
     */
    public static String getAlphanumericString(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    /**
     * 创建一个随机字符串，其长度介于包含最小值和最大最大值之间,，字符将从拉丁字母（a-z、A-Z）和数字0-9中选择
     *
     * @param min 最小长度
     * @param max 最大长度
     * @return
     */
    public static String getAlphanumericString(int min, int max) {
        return RandomStringUtils.randomAlphanumeric(min, max);
    }
}
