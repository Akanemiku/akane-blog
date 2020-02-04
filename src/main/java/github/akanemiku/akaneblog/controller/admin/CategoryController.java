package github.akanemiku.akaneblog.controller.admin;

import github.akanemiku.akaneblog.constant.Types;
import github.akanemiku.akaneblog.constant.WebConst;
import github.akanemiku.akaneblog.dto.MetaDTO;
import github.akanemiku.akaneblog.exception.InternalException;
import github.akanemiku.akaneblog.model.Meta;
import github.akanemiku.akaneblog.service.MetaService;
import github.akanemiku.akaneblog.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    private MetaService metaService;

    @GetMapping(value = "")
    public String index(HttpServletRequest request) {
        // 获取所有目录分类
        List<MetaDTO> categories = metaService.getMetaList(Types.CATEGORY.getType(),null, WebConst.MAX_POSTS);
        // 获取所有标签分类
        List<MetaDTO> tags = metaService.getMetaList(Types.TAG.getType(), null, WebConst.MAX_POSTS);
        request.setAttribute("categories",categories);
        request.setAttribute("tags",tags);
        return "admin/category";
    }

    @PostMapping(value = "/save")
    @ResponseBody
    public APIResponse addCategory(@RequestParam(name = "cname", required = true) String cname,
                                   @RequestParam(name = "mid", required = false) Integer mid){
        Meta meta = new Meta();
        meta.setName(cname);
        meta.setMid(mid);
        meta.setSlug(cname);
        meta.setType(Types.CATEGORY.getType());
        try{
            metaService.saveMeta(meta);
        }catch (InternalException e){
            e.printStackTrace();
            String msg = "分类保存失败";
            return APIResponse.failure(msg);
        }

        return APIResponse.success();
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public APIResponse deleteCategory( @RequestParam(name = "mid", required = true) Integer mid){
        try {
            metaService.deleteById(mid);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.failure(e.getMessage());
        }
        return APIResponse.success();
    }
}
