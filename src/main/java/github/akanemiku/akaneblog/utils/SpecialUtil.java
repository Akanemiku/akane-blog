package github.akanemiku.akaneblog.utils;

import org.apache.commons.lang3.StringUtils;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.renderer.html.HtmlRenderer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

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
    public static String mdToHtml(String markdown) {
        if (StringUtils.isBlank(markdown)) {
            return "";
        }
        java.util.List<Extension> extensions = Arrays.asList(TablesExtension.create());
        org.commonmark.parser.Parser parser = org.commonmark.parser.Parser.builder().extensions(extensions).build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().extensions(extensions).build();
        String content = renderer.render(document);
        content = Commons.emoji(content);
        return content;

    }

}
