package github.akanemiku.akaneblog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 评论状态
 */
@Getter
@AllArgsConstructor
public enum  CommentEnum {
    CHECKED(1),
    UNCHECKED(0);

    private Integer type;
}
