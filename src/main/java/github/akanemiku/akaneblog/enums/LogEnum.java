package github.akanemiku.akaneblog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 日志表动作
 */
@Getter
@AllArgsConstructor
public enum LogEnum {

    LOGIN("登录后台"),
    UP_PWD("修改密码"),
    UP_INFO("修改个人信息"),
    DEL_ARTICLE("删除文章"),
    SYS_SETTING("保存系统设置");

    private String action;
}
