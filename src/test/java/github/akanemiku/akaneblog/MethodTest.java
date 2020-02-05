package github.akanemiku.akaneblog;

import github.akanemiku.akaneblog.dto.MetaDTO;
import github.akanemiku.akaneblog.model.Content;
import github.akanemiku.akaneblog.model.Relation;
import github.akanemiku.akaneblog.repository.ContentRepository;
import github.akanemiku.akaneblog.repository.MetaRepository;
import github.akanemiku.akaneblog.repository.RelationRepository;
import github.akanemiku.akaneblog.repository.dao.MetaDao;
import github.akanemiku.akaneblog.utils.Commons;
import github.akanemiku.akaneblog.utils.RedisUtil;
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
    private RedisUtil redis;
    @Test
    public void test1(){
        redis.set("a","111",10);
        redis.set("bbb",12345);
        redis.set("login_status",2,15);
        System.out.println(redis.get("a"));
        System.out.println(redis.get("bbb"));
        Integer login_status = (Integer) redis.get("login_status");
        System.out.println(login_status);
    }

}
