package github.akanemiku.akaneblog.controller;

import github.akanemiku.akaneblog.constant.Types;
import github.akanemiku.akaneblog.constant.WebConst;
import github.akanemiku.akaneblog.dto.MetaDTO;
import github.akanemiku.akaneblog.model.Content;
import github.akanemiku.akaneblog.service.ContentService;
import github.akanemiku.akaneblog.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ContentService contentService;
    @Autowired
    private MetaService metaService;

    @GetMapping("/")
    public String index(@RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
                        @RequestParam(value = "pageSize", defaultValue = "1", required = false) Integer pageSize,
                        HttpServletRequest request) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Content> articles = contentService.getAllArticles(pageable);
        request.setAttribute("articles", articles);

        return "blog/home";
    }

    @GetMapping("/archives")
    public String archives(@RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
                           @RequestParam(value = "pageSize", defaultValue = "1", required = false) Integer pageSize,
                           HttpServletRequest request) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Content> articles = contentService.getAllArticles(pageable);
        request.setAttribute("articles", articles);

        return "blog/archives";
    }

    @GetMapping(value = "/categories")
    public String categories(HttpServletRequest request) {
        //获取category类型的所有数据
        List<MetaDTO> categories = metaService.getMetaList(Types.CATEGORY.getType(), null, WebConst.MAX_POSTS);
        // category类型总数
        Long categoryCount = metaService.getMetasCountByType(Types.CATEGORY.getType());
        request.setAttribute("categories", categories);
        request.setAttribute("categoryCount", categoryCount);
        return "blog/category";
    }
}
