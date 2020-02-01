package github.akanemiku.akaneblog.controller.admin;

import github.akanemiku.akaneblog.dto.StatisticsDTO;
import github.akanemiku.akaneblog.model.Comment;
import github.akanemiku.akaneblog.model.Content;
import github.akanemiku.akaneblog.model.Log;
import github.akanemiku.akaneblog.service.LogService;
import github.akanemiku.akaneblog.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class IndexController {
    @Autowired
    private MonitorService monitorService;
    @Autowired
    private LogService logService;

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
}
