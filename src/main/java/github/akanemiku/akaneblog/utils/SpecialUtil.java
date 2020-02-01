package github.akanemiku.akaneblog.utils;

import github.akanemiku.akaneblog.utils.markdown.MarkDownToHtmlWrapper;
import github.akanemiku.akaneblog.utils.markdown.MarkdownEntity;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SpecialUtil {

    /**
     * MD5加密
     * @param source 数据源
     * @return  加密字符串
     */
    public static String MD5encode(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        MessageDigest messageDigest = null;
        try {
            // 得到一个信息摘要器
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] encode = messageDigest.digest(source.getBytes());
        StringBuffer hexString = new StringBuffer();
        // 把每一个byte 做一个与运算 0xff;
        for (byte anEncode : encode) {
            // 与运算
            String hex = Integer.toHexString(0xff & anEncode);// 加盐
            if (hex.length() == 1) {
                hexString.append("0");
            }
            hexString.append(hex);
        }
        // 标准的md5加密后的结果
        return hexString.toString();

    }

    /**
     * markdown转换为html
     * @param markdown
     * @return
     */
    public static String markdownToHtml(String markdown) {
        if (StringUtils.isBlank(markdown)) {
            return "";
        }
        MarkdownEntity html = MarkDownToHtmlWrapper.ofContent(markdown);
        return html.toString();
    }

}
