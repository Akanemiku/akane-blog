package github.akanemiku.akaneblog.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Types {

    // 分类
    CATEGORY("category"),
    // 标签
    TAG("tag"),
    // 文章
    ARTICLE("post"),
    // token
    TOKEN("token"),
    // 友情链接
    LINK("link"),
    // 评论
    COMMENT("comment"),
    // 图片
    IMAGE("image");

    private String type;
}

