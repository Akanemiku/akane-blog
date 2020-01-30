package github.akanemiku.akaneblog.constant;

public enum Types {

    // 分类
    CATEGORY("category"),
    // 标签
    TAG("tag"),
    // 文章
    ARTICLE("post"),
    // csrf_token
    CSRF_TOKEN("csrf_token"),
    // 友情链接
    LINK("link"),
    // 评论
    COMMENTS_FREQUENCY("comments:frequency"),
    // 图片
    IMAGE("image"),
    // 文件
    FILE("file"),
    /**
     * 附件存在的URL，默认为网站地址，如集成第三方则为第三方CND域名
     */
    // ATTACH_URL("attach_url");
    ATTACH_URL("http://pb84kab39.bkt.clouddn.com/");

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

