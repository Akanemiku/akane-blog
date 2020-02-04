package github.akanemiku.akaneblog.controller.admin;

import github.akanemiku.akaneblog.constant.Types;
import github.akanemiku.akaneblog.enums.ErrorEnum;
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
@RequestMapping("/admin/links")
public class LinkController {
    @Autowired
    private MetaService metaService;

    @GetMapping(value = "")
    public String index(HttpServletRequest request) {
        List<Meta> metaList = metaService.getMetasByType(Types.LINK.getType());
        request.setAttribute("links", metaList);
        return "admin/links";
    }

    @PostMapping(value = "/save")
    @ResponseBody
    public APIResponse addLink(@RequestParam(name = "title", required = true) String title,
                               @RequestParam(name = "url", required = true) String url,
                               @RequestParam(name = "logo", required = false) String logo,
                               @RequestParam(name = "mid", required = false) Integer mid,
                               @RequestParam(name = "sort", required = false, defaultValue = "0") int sort) {
        try {
            Meta meta = new Meta();
            meta.setMid(mid);
            meta.setName(title);
            meta.setSlug(url);
            meta.setDescription(logo);
            meta.setSort(sort);
            meta.setType(Types.LINK.getType());
            metaService.saveLink(meta);
        } catch (InternalException e) {
            e.printStackTrace();
            throw new InternalException(ErrorEnum.ADD_META_FAIL);
        }

        return APIResponse.success();

    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public APIResponse deleteLink(@RequestParam(name = "mid", required = true) int mid) {
        try {
            metaService.deleteById(mid);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalException(ErrorEnum.DELETE_META_FAIL);
        }
        return APIResponse.success();
    }
}
