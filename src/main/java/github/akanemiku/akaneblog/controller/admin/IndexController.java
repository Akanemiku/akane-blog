package github.akanemiku.akaneblog.controller.admin;

import github.akanemiku.akaneblog.constant.WebConst;
import github.akanemiku.akaneblog.dto.StatisticsDTO;
import github.akanemiku.akaneblog.enums.LogEnum;
import github.akanemiku.akaneblog.exception.InternalException;
import github.akanemiku.akaneblog.model.Comment;
import github.akanemiku.akaneblog.model.Content;
import github.akanemiku.akaneblog.model.Log;
import github.akanemiku.akaneblog.model.User;
import github.akanemiku.akaneblog.service.LogService;
import github.akanemiku.akaneblog.service.MonitorService;
import github.akanemiku.akaneblog.service.UserService;
import github.akanemiku.akaneblog.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class IndexController {
    @Autowired
    private MonitorService monitorService;
    @Autowired
    private LogService logService;
    @Autowired
    private UserService userService;

    /**
     * 首页
     * @param request
     * @return
     */
    @GetMapping(value = {"", "/index"})
    public String index(HttpServletRequest request) {
        // 获取5篇文章
        List<Content> contentList = monitorService.getNewArticles(5);
        // 获取5条评论
        List<Comment> commentList = monitorService.getNewComment(5);
        // 获取后台统计数
        StatisticsDTO statisticsDTO = monitorService.getStatistics();
        // 获取5篇日志
        List<Log> logList = logService.getNewLog(5);

        request.setAttribute("comments",commentList);
        request.setAttribute("articles",contentList);
        request.setAttribute("statistics",statisticsDTO);
        request.setAttribute("logs",logList);
        return "admin/index";
    }

    /**
     * 个人设置页
     */
    @GetMapping(value = "/profile")
    public String profile() {
        return "admin/profile";
    }

    /**
     * 保存个人信息
     * @param screenName
     * @param email
     * @param request
     * @return
     */
    @PostMapping(value = "/profile")
    @ResponseBody
    public APIResponse saveProfile(@RequestParam(value = "screenName") String screenName,
                                   @RequestParam(value = "email") String email,
                                   HttpServletRequest request){
        // 获得登录用户
        User user = (User) request.getSession().getAttribute(WebConst.LOGIN_SESSION_KEY);
        User temp = userService.getUserById(user.getUid());
        temp.setScreenName(screenName);
        temp.setEmail(email);
        //更新用户
        userService.saveUser(user);

        Log log = Commons.newLog(LogEnum.UP_INFO.getAction(),temp,temp.getUid(),request);
        //写入日志
        logService.insertLog(log);

        //更新session中数据
        User sessionUser = (User) request.getSession().getAttribute(WebConst.LOGIN_SESSION_KEY);
        sessionUser.setScreenName(screenName);
        sessionUser.setEmail(email);
        request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY,sessionUser);
        return APIResponse.success();
    }

    @PostMapping(value = "/password")
    @ResponseBody
    public APIResponse upPwd(@RequestParam(value = "oldPassword") String oldPassword,
                             @RequestParam(value = "newPassword") String newPassword,
                             HttpServletRequest request){
        // 获得登录用户
        User user = (User) request.getSession().getAttribute(WebConst.LOGIN_SESSION_KEY);
        System.out.println("password: "+user.toString());
        if (StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword)) {
            return APIResponse.failure("请确认信息输入完整");
        }

        if (!user.getPassword().equals(SpecialUtil.MD5encode(oldPassword))) {
            return APIResponse.failure("旧密码错误");
        }

        if (newPassword.length() < 6 || newPassword.length() > 14) {
            return APIResponse.failure("请输入6-14位密码");
        }
        try{
            User temp = userService.getUserById(user.getUid());
            temp.setPassword(SpecialUtil.MD5encode(newPassword));
            userService.saveUser(user);

            //写入日志
            logService.insertLog(Commons.newLog(LogEnum.UP_PWD.getAction(),temp,temp.getUid(),request));
            //更新session中数据
            User sessionUser = (User) request.getSession().getAttribute(WebConst.LOGIN_SESSION_KEY);
            sessionUser.setPassword(SpecialUtil.MD5encode(newPassword));
            request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY,sessionUser);
            return APIResponse.success();
        }catch (InternalException e){
            String msg = "密码修改失败";
            return APIResponse.failure(msg);
        }

    }


}
