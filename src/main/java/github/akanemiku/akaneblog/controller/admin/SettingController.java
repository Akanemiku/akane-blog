package github.akanemiku.akaneblog.controller.admin;

import github.akanemiku.akaneblog.constant.WebConst;
import github.akanemiku.akaneblog.enums.LogEnum;
import github.akanemiku.akaneblog.exception.InternalException;
import github.akanemiku.akaneblog.model.Log;
import github.akanemiku.akaneblog.model.Option;
import github.akanemiku.akaneblog.model.User;
import github.akanemiku.akaneblog.service.LogService;
import github.akanemiku.akaneblog.service.OptionService;
import github.akanemiku.akaneblog.utils.APIResponse;
import github.akanemiku.akaneblog.utils.Commons;
import github.akanemiku.akaneblog.utils.IPUtils;
import github.akanemiku.akaneblog.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin/setting")
public class SettingController {

    @Autowired
    private OptionService optionService;
    @Autowired
    private LogService logService;

    @GetMapping(value = "")
    public String index(HttpServletRequest request) {
        List<Option> optionsList = optionService.getOptions();
        Map<String, String> options = new HashMap<>();
        optionsList.forEach((option) ->{
            options.put(option.getName(),option.getValue());
        });
        request.setAttribute("options", options);
        //thymeleaf中options.key/options.value取键与值
        return "admin/setting";
    }

    @PostMapping(value = "/save")
    @ResponseBody
    public APIResponse saveSetting(HttpServletRequest request){
        try{
            Map<String, String[]> parameterMap = request.getParameterMap();
            Map<String,String> optionsMap = new HashMap<>();
            //转换map
            parameterMap.forEach((k,v)->{
                optionsMap.put(k, Commons.arrayToString(v));
            });
            optionService.saveOptions(optionsMap);

            // 写入日志
            User user = (User) request.getSession().getAttribute(WebConst.LOGIN_SESSION_KEY);
            Log log = Commons.newLog(LogEnum.SYS_SETTING.getAction(), JsonUtil.toJsonString(optionsMap), user.getUid(),request);
            logService.insertLog(log);

            return APIResponse.success();
        }catch (InternalException e){
            e.printStackTrace();
            return APIResponse.failure(e.getMessage());
        }
    }
}
