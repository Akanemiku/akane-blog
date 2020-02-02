package github.akanemiku.akaneblog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 评论状态
 */
@Getter
@AllArgsConstructor
public enum  CommentEnum {
    STATUS_NORMAL("approved"),
    STATUS_BLANK("not_audit");

    private String type;
}
