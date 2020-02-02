package github.akanemiku.akaneblog.controller.admin;

import github.akanemiku.akaneblog.constant.Types;
import github.akanemiku.akaneblog.model.Content;
import github.akanemiku.akaneblog.model.Meta;
import github.akanemiku.akaneblog.service.ContentService;
import github.akanemiku.akaneblog.service.LogService;
import github.akanemiku.akaneblog.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin/article")
public class ArticleController {
    @Autowired
    private MetaService metaService;
    @Autowired
    private ContentService contentService;
    @Autowired
    private LogService logService;

    /**
     * 文章管理主页面
     * @param pageNo
     * @param pageSize
     * @param request
     * @return
     */
    @GetMapping("")
    public String index(@RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
                        @RequestParam(value = "pageSize", defaultValue = "1", required = false) Integer pageSize,
                        HttpServletRequest request){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Content> articles = contentService.getAllArticles(pageable);
        request.setAttribute("articles", articles);
        return "admin/article_list";
    }

    /**
     * 发布文章页面
     * @param request
     * @return
     */
    @GetMapping(value = "/publish")
    public String newArticle(HttpServletRequest request) {
        List<Meta> categories = metaService.getMetasByType(Types.CATEGORY.getType());
        request.setAttribute("categories",categories);
        return "admin/article_edit";
    }

    /**
     * 文章编辑页面
     * @param cid
     * @param request
     * @return
     */
    @GetMapping(value = "/{cid}")
    public String editArticle(@PathVariable(value = "cid",required = true) Integer cid,
                              HttpServletRequest request){
        Content content =contentService.getArticleById(cid);
        List<Meta> categories = metaService.getMetasByType(Types.CATEGORY.getType());
        request.setAttribute("contents", content);
        request.setAttribute("categories", categories);
        request.setAttribute("active", "article");
        return "admin/article_edit";
    }

}
