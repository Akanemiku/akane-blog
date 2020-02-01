package github.akanemiku.akaneblog.controller.admin;

import github.akanemiku.akaneblog.dto.StatisticsDTO;
import github.akanemiku.akaneblog.model.Comment;
import github.akanemiku.akaneblog.model.Content;
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

    @GetMapping(value = {"","/index"})
    public String index(HttpServletRequest request) {
        List<Content> contentList = monitorService.getNewArticles(5);
        List<Comment> commentList = monitorService.getNewComment(5);
        StatisticsDTO statisticsDTO = monitorService.getStatistics();



        return "admin/index";
    }
}
