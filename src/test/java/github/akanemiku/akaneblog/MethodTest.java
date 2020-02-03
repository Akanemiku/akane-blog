package github.akanemiku.akaneblog;

import github.akanemiku.akaneblog.dto.MetaDTO;
import github.akanemiku.akaneblog.model.Content;
import github.akanemiku.akaneblog.model.Relation;
import github.akanemiku.akaneblog.repository.ContentRepository;
import github.akanemiku.akaneblog.repository.MetaRepository;
import github.akanemiku.akaneblog.repository.RelationRepository;
import github.akanemiku.akaneblog.repository.dao.MetaDao;
import github.akanemiku.akaneblog.utils.SpecialUtil;
import org.apache.commons.lang3.StringUtils;
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
    @Autowired
    private MetaRepository metaRepository;
    @Autowired
    private RelationRepository relationRepository;
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

    @Test
    public void tes3(){
        List<Relation> relationList = relationRepository.findRelationByMid(54);
        System.out.println(relationList.toString());
    }

    private String reMeta(String name, String metas) {
        String[] ms = StringUtils.split(metas,",");
        StringBuilder buf = new StringBuilder();
        for (String m : ms) {
            if (!name.equals(m)) {
                buf.append(",").append(m);
            }
        }
        if (buf.length() > 0) {
            return buf.substring(1);
        }
        return "";
    }

    @Test
    public void test4(){
        String str = reMeta("默认分类","默认分类,测试分类");
        System.out.println(str);
        str = reMeta("tag2","tag1,tag2,tag3");
        System.out.println(str);
        str = reMeta("隔热个人","说的发顺丰,隔热个人,问题问题");
        System.out.println(str);

    }

    @Test
    public void test5(){
        relationRepository.deleteByMid(67);
    }

}
