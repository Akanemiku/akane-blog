package github.akanemiku.akaneblog.controller.admin;

import github.akanemiku.akaneblog.constant.WebConst;
import github.akanemiku.akaneblog.exception.InternalException;
import github.akanemiku.akaneblog.model.User;
import github.akanemiku.akaneblog.service.UserService;
import github.akanemiku.akaneblog.utils.APIResponse;
import github.akanemiku.akaneblog.utils.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/login")
    public String login() {
        return "admin/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public APIResponse toLogin(@RequestParam(name = "username", required = true) String username,
                               @RequestParam(name = "password", required = true) String password,
                               @RequestParam(name = "remember_me", required = false) String remember_me,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        try {
            // 调用Service登录方法
            User user = userService.login(username, password);
            // 设置登录用户session
            request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, user);
            // 判断是否勾选记住我
            if (StringUtils.isNotBlank(remember_me)) {
                CookieUtil.setCookie(response, WebConst.USER_IN_COOKIE, String.valueOf(user.getUid()));
            }
            // TODO 写入日志
        } catch (InternalException e) {
            // TODO 多次输入密码限制
            // TODO 错误类型不符判断
            String msg = e.getMessage();
            return APIResponse.failure(msg);
        }
        return APIResponse.success();
    }

    @RequestMapping(value = "/logout")
    public void logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        // 移除session
        session.removeAttribute(WebConst.LOGIN_SESSION_KEY);
        // 移除cookie
        CookieUtil.removeCookie(request,response,WebConst.USER_IN_COOKIE);
        // 跳转到登录页面
        try {
            response.sendRedirect("/admin/login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
