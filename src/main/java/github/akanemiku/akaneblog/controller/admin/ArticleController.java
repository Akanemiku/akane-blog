package github.akanemiku.akaneblog.controller.admin;

import github.akanemiku.akaneblog.constant.Types;
import github.akanemiku.akaneblog.constant.WebConst;
import github.akanemiku.akaneblog.enums.LogEnum;
import github.akanemiku.akaneblog.model.Content;
import github.akanemiku.akaneblog.model.Log;
import github.akanemiku.akaneblog.model.Meta;
import github.akanemiku.akaneblog.model.User;
import github.akanemiku.akaneblog.service.ContentService;
import github.akanemiku.akaneblog.service.LogService;
import github.akanemiku.akaneblog.service.MetaService;
import github.akanemiku.akaneblog.utils.APIResponse;
import github.akanemiku.akaneblog.utils.Commons;
import github.akanemiku.akaneblog.utils.DateUtil;
import github.akanemiku.akaneblog.utils.IPUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
     *
     * @param pageNo
     * @param pageSize
     * @param request
     * @return
     */
    @GetMapping("")
    public String index(@RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
                        @RequestParam(value = "pageSize", defaultValue = "1", required = false) Integer pageSize,
                        HttpServletRequest request) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Content> articles = contentService.getAllArticles(pageable);
        request.setAttribute("articles", articles);
        return "admin/article_list";
    }

    /**
     * 发布文章页面
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/publish")
    public String newArticle(HttpServletRequest request) {
        List<Meta> categories = metaService.getMetasByType(Types.CATEGORY.getType());
        request.setAttribute("categories", categories);
        return "admin/article_edit";
    }

    /**
     * 文章编辑页面
     *
     * @param cid
     * @param request
     * @return
     */
    @GetMapping(value = "/{cid}")
    public String editArticle(@PathVariable(value = "cid", required = true) Integer cid,
                              HttpServletRequest request) {
        Content content = contentService.getArticleById(cid);
        List<Meta> categories = metaService.getMetasByType(Types.CATEGORY.getType());
        request.setAttribute("contents", content);
        request.setAttribute("categories", categories);
        request.setAttribute("active", "article");
        return "admin/article_edit";
    }

    @PostMapping("/modify")
    @ResponseBody
    public APIResponse modifyArticle(@RequestParam(name = "cid", required = true) Integer cid,
                                     @RequestParam(name = "title", required = true) String title,
                                     @RequestParam(name = "titlePic", required = false) String titlePic,
                                     @RequestParam(name = "slug", required = false) String slug,
                                     @RequestParam(name = "content", required = true) String content,
                                     @RequestParam(name = "type", required = true) String type,
                                     @RequestParam(name = "status", required = true) String status,
                                     @RequestParam(name = "tags", required = false) String tags,
                                     @RequestParam(name = "categories", required = false, defaultValue = "默认分类") String categories,
                                     @RequestParam(name = "allowComment", required = true) Boolean allowComment) {
        Content article = contentService.getArticleById(cid);
        article.setTitle(title);
        article.setCid(cid);
        article.setTitlePic(titlePic);
        article.setSlug(slug);
        article.setContent(content);
        article.setModified(DateUtil.getCurrentUnixTime());
        article.setType(type);
        article.setStatus(status);
        article.setTags(tags);
        article.setCategories(categories);
        article.setAllowComment(allowComment ? 1 : 0);
        //更新文章
        contentService.saveContent(article);
        return APIResponse.success();
    }

    @PostMapping(value = "/publish")
    @ResponseBody
    public APIResponse publishArticle(@RequestParam(name = "title", required = true) String title,
                                      @RequestParam(name = "titlePic", required = false) String titlePic,
                                      @RequestParam(name = "slug", required = false) String slug,
                                      @RequestParam(name = "content", required = true) String content,
                                      @RequestParam(name = "type", required = true) String type,
                                      @RequestParam(name = "status", required = true) String status,
                                      @RequestParam(name = "categories", required = false, defaultValue = "默认分类") String categories,
                                      @RequestParam(name = "tags", required = false) String tags,
                                      @RequestParam(name = "allowComment", required = true) Boolean allowComment) {
        Content article = new Content();
        article.setTitle(title);
        article.setTitlePic(titlePic);
        article.setSlug(slug);
        article.setContent(content);
        article.setCreated(DateUtil.getCurrentUnixTime());
        article.setType(type);
        article.setStatus(status);
        article.setHits(1);
        article.setCommentsNum(0);
        // 只允许博客文章有分类，防止作品被收入分类
        article.setTags(type.equals(Types.ARTICLE.getType()) ? tags : null);
        article.setCategories(type.equals(Types.ARTICLE.getType()) ? categories : null);
        article.setAllowComment(allowComment ? 1 : 0);
        // 添加文章
        contentService.saveContent(article);

        return APIResponse.success();
    }

    @PostMapping("/delete")
    @ResponseBody
    public APIResponse deleteArticle(@RequestParam(name = "cid", required = true) Integer cid,
                                     HttpServletRequest request
    ) {
        // 删除文章
        contentService.deleteArticleById(cid);
        // 写入日志
        User user = (User) request.getSession().getAttribute(WebConst.LOGIN_SESSION_KEY);
        Log log = Commons.newLog(LogEnum.DEL_ARTICLE.getAction(),""+cid,user.getUid(),request);
        logService.insertLog(log);
        return APIResponse.success();
    }

}
