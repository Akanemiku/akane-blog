package github.akanemiku.akaneblog.controller.admin;

import github.akanemiku.akaneblog.constant.Types;
import github.akanemiku.akaneblog.constant.WebConst;
import github.akanemiku.akaneblog.dto.MetaDTO;
import github.akanemiku.akaneblog.service.MetaService;
import github.akanemiku.akaneblog.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    private MetaService metaService;

    @GetMapping(value = "")
    public String index(HttpServletRequest request) {
        // 获取所欲目录分类
        List<MetaDTO> categories = metaService.getMetaList(Types.CATEGORY.getType(),null, WebConst.MAX_POSTS);
        // 获取所有标签分类
        List<MetaDTO> tags = metaService.getMetaList(Types.TAG.getType(), null, WebConst.MAX_POSTS);
        request.setAttribute("categories",categories);
        request.setAttribute("tags",tags);
        return "admin/category";
    }

    @PostMapping(value = "/save")
    @ResponseBody
    public APIResponse addCategory(){
        return APIResponse.success();
    }
}
