package github.akanemiku.akaneblog.interceptor;

import github.akanemiku.akaneblog.constant.Types;
import github.akanemiku.akaneblog.constant.WebConst;
import github.akanemiku.akaneblog.dto.MetaDTO;
import github.akanemiku.akaneblog.model.Option;
import github.akanemiku.akaneblog.service.ContentService;
import github.akanemiku.akaneblog.service.MetaService;
import github.akanemiku.akaneblog.service.OptionService;
import github.akanemiku.akaneblog.utils.Commons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BaseInterceptor implements HandlerInterceptor {

    @Autowired
    private OptionService optionService;
    @Autowired
    private Commons commons;
    @Autowired
    private MetaService metaService;
    @Autowired
    private ContentService contentService;
    @Autowired
    private HttpSession session;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 请求URL不包含域名
        String uri = request.getRequestURI();

        HttpSession session = request.getSession();
        Object user = session.getAttribute(WebConst.LOGIN_SESSION_KEY);

        if(null == user && uri.startsWith("/admin") && !uri.startsWith("/admin/login")
                && !uri.startsWith("/admin/css") && !uri.startsWith("/admin/images")
                && !uri.startsWith("/admin/js") && !uri.startsWith("/admin/plugins")
                && !uri.startsWith("/admin/editormd")){
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //获得网站备案
        Option option = optionService.getByName("site_record");
        // 分类总数
        Long categoryCount = metaService.getMetasCountByType(Types.CATEGORY.getType());
        // 标签总数
        Long tagCount = metaService.getMetasCountByType(Types.TAG.getType());
        // 获取文章总数
        Long contentCount = contentService.getContentsCount();
        // 获取友情链接
        List<MetaDTO> links = metaService.getMetaList(Types.LINK.getType(),null, WebConst.MAX_POSTS);

        session.setAttribute("categoryCount",categoryCount);
        session.setAttribute("tagCount",tagCount);
        session.setAttribute("articleCount",contentCount);
        session.setAttribute("links",links);
        request.setAttribute("commons", commons);
        request.setAttribute("option", option);
        //加载配置项
        initSiteConfig();
    }

    private void initSiteConfig() {
        if (WebConst.initConfig.isEmpty()) {
            List<Option> options = optionService.getOptions();
            Map<String, String> querys = new HashMap<>();
            options.forEach(option -> {
                querys.put(option.getName(),option.getValue());
            });
            WebConst.initConfig = querys;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
