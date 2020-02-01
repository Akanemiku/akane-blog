package github.akanemiku.akaneblog;

import github.akanemiku.akaneblog.dto.MetaDTO;
import github.akanemiku.akaneblog.model.Content;
import github.akanemiku.akaneblog.model.Relation;
import github.akanemiku.akaneblog.repository.ContentRepository;
import github.akanemiku.akaneblog.repository.MetaRepository;
import github.akanemiku.akaneblog.repository.dao.MetaDao;
import github.akanemiku.akaneblog.utils.SpecialUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MethodTest {
    @Autowired
    private MetaDao dao;
    @Autowired
    private ContentRepository contentRepository;
    @Test
    public void test1(){
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("type", "category");
        paraMap.put("order", "count desc, a.mid desc");
        paraMap.put("limit", 5);
        List<MetaDTO> metaDTOS = dao.selectFromSql(paraMap);
        System.out.println(metaDTOS.toString());
    }

    @Test
    public void test2(){
        System.out.println(SpecialUtil.MD5encode("123456"));
    }

}
