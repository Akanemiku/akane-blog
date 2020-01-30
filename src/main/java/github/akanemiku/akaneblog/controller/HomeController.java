package github.akanemiku.akaneblog.controller;

import github.akanemiku.akaneblog.model.Content;
import github.akanemiku.akaneblog.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @Autowired
    private ContentService contentService;

    @GetMapping("/")
    public String index(@RequestParam(value = "pageNo",defaultValue = "0",required = false) Integer pageNo,
                        @RequestParam(value = "pageSize",defaultValue = "1",required = false)Integer pageSize,
                        HttpServletRequest request){

        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Content> articles = contentService.getAllArticles(pageable);
        request.setAttribute("articles",articles);

        return "blog/home";
    }
}
