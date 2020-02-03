package github.akanemiku.akaneblog.constant;

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
    IMAGE("image"),
    // 文件
    FILE("file");

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    Types(String type) {
        this.type = type;
    }
}

