package github.akanemiku.akaneblog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorEnum {
    // common
    PARAM_IS_EMPTY(501, "参数为空"),

    // auth
    USERNAME_PASSWORD_ERROR(100, "用户名不存在或密码错误"),
    USERNAME_PASSWORD_IS_EMPTY(101, "用户名和密码不可为空"),

    // meta
    ADD_META_FAIL(130, "添加项目信息失败"),
    DELETE_META_FAIL(131, "删除项目信息失败"),
    NOT_ONE_RESULT(132, "获取的项目的数量不止一个"),
    META_IS_EXIST(133, "该项目已经存在");

    private Integer code;
    private String message;
}
