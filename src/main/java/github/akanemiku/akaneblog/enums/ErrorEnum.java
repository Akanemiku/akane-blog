package github.akanemiku.akaneblog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorEnum {

    //auth
    USERNAME_PASSWORD_ERROR(100,"用户名不存在或密码错误"),
    USERNAME_PASSWORD_IS_EMPTY(101,"用户名和密码不可为空");

    private Integer code;
    private String message;
}
