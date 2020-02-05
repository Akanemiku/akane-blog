package github.akanemiku.akaneblog.controller;

import github.akanemiku.akaneblog.constant.Types;
import github.akanemiku.akaneblog.constant.WebConst;
import github.akanemiku.akaneblog.dto.MetaDTO;
import github.akanemiku.akaneblog.model.Comment;
import github.akanemiku.akaneblog.model.Content;
import github.akanemiku.akaneblog.model.Meta;
import github.akanemiku.akaneblog.service.CommentService;
import github.akanemiku.akaneblog.service.ContentService;
import github.akanemiku.akaneblog.service.MetaService;
import github.akanemiku.akaneblog.utils.APIResponse;
import github.akanemiku.akaneblog.utils.IPUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ContentService contentService;
    @Autowired
    private MetaService metaService;
    @Autowired
    private CommentService commentService;

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

    @GetMapping(value = "/categories/{name}")
    public String categoriesDetail(@PathVariable("name") String name,
                                   HttpServletRequest request) {

        Meta category = metaService.getMetaByNameAndType(Types.CATEGORY.getType(), name);
        // TODO category name为空判断
        List<Content> articles = contentService.getArticleByCategory(category.getName());
        request.setAttribute("category", category.getName());
        request.setAttribute("articles", articles);
        return "blog/category_detail";
    }

    @GetMapping(value = "/tags")
    public String tags(HttpServletRequest request) {
        // 获取标签
        List<MetaDTO> tags = metaService.getMetaList(Types.TAG.getType(), null, WebConst.MAX_POSTS);
        // 标签总数
        Long tagCount = metaService.getMetasCountByType(Types.TAG.getType());
        request.setAttribute("tags", tags);
        request.setAttribute("tagCount", tagCount);
        return "blog/tags";
    }

    @GetMapping(value = "/tags/{name}")
    public String tagsDetail(@PathVariable("name") String name,
                             HttpServletRequest request) {
        Meta tags = metaService.getMetaByNameAndType(Types.TAG.getType(), name);
        List<Content> articles = contentService.getArticleByTag(tags);
        request.setAttribute("articles", articles);
        request.setAttribute("tags", tags.getName());
        return "blog/tags_detail";
    }

    @GetMapping(value = "/about")
    public String about() {
        return "blog/about";
    }

    @GetMapping(value = "/detail/{cid}")
    public String detail(@PathVariable("cid") Integer cid,
                         HttpServletRequest request) {
        //获取文章内容
        Content article = contentService.getArticleById(cid);
        request.setAttribute("article", article);
        //更新点击次数
        updateArticleHits(article, article.getHits());
        //获取评论
        List<Comment> comments = commentService.getCommentsByCid(cid);
        request.setAttribute("comments", comments);
        return "blog/detail";
    }

    private void updateArticleHits(Content article, Integer hits) {
        if (hits == null) {
            hits = 0;
        }
        // TODO 多点防护措施
        article.setHits(hits + 1);
        System.out.println(article.toString());
        contentService.updateContent(article);
    }

    @PostMapping("/comment")
    @ResponseBody
    public APIResponse comment(Comment comment,
                               HttpServletRequest request) {
        // TODO 各种权限的判断和内容限制，前端+后台，可能直接通过接口进行攻击故后台也需拦截
        System.out.println(comment.toString());

        String ref = request.getHeader("Referer");
        if (StringUtils.isBlank(ref)) {
            return APIResponse.failure("访问失败");
        }
        // TODO 可能存在的输入异常，如用户输入特殊字符、html等，需要对数据进行处理
        // TODO 连续评论禁止
        comment.setIp(IPUtils.getIpAddress(request));
        commentService.insertComment(comment);
        return APIResponse.success();
    }

}
