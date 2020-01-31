package github.akanemiku.akaneblog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  CommentEnum {
    STATUS_NORMAL("approved"),
    STATUS_BLANK("not_audit");

    private String type;
}
