package github.akanemiku.akaneblog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class IndexController {

    @GetMapping(value = {"","/index"})
    public String index(HttpServletRequest request) {
        return "admin/index";
    }
}
