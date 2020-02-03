package github.akanemiku.akaneblog.service.impl;

import github.akanemiku.akaneblog.constant.WebConst;
import github.akanemiku.akaneblog.dto.MetaDTO;
import github.akanemiku.akaneblog.enums.ErrorEnum;
import github.akanemiku.akaneblog.exception.InternalException;
import github.akanemiku.akaneblog.model.Meta;
import github.akanemiku.akaneblog.model.Relation;
import github.akanemiku.akaneblog.repository.MetaRepository;
import github.akanemiku.akaneblog.repository.RelationRepository;
import github.akanemiku.akaneblog.repository.dao.MetaDao;
import github.akanemiku.akaneblog.service.MetaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MetaServiceImpl implements MetaService {

    @Autowired
    private MetaDao metaDao;
    @Autowired
    private MetaRepository metaRepository;
    @Autowired
    private RelationRepository relationRepository;

    @Override
    public List<MetaDTO> getMetaList(String type, String orderBy, int limit) {
        if (StringUtils.isNotBlank(type)) {
            if (StringUtils.isBlank(orderBy)) {
                orderBy = "count desc, a.mid desc";
            }
            if (limit < 1 || limit > WebConst.MAX_POSTS) {
                limit = 10;
            }
            Map<String, Object> paraMap = new HashMap<>();
            paraMap.put("type", type);
            paraMap.put("order", orderBy);
            paraMap.put("limit", limit);
            return metaDao.selectFromSql(paraMap);
        }
        return null;
    }

    @Override
    public List<Meta> getMetasByType(String type) {
        return metaRepository.findAllByType(type);
    }

    @Override
    public Long getMetasCountByType(String type) {
        return metaRepository.findMetasCountByType(type);
    }

    @Override
    public Meta getMetaByNameAndType(String type, String name) {
        // TODO name为空异常
        return metaRepository.findMetaByNameAndType(name,type);
    }

    @Override
    public void saveMeta(Meta meta) {
        // 通过项目名和类型查找有没有存在的
        Meta temp = metaRepository.findMetaByNameAndType(meta.getName(),meta.getType());
        // 判断是否找到有相同的
        if(null == temp){
            // 如果有mid则为更新
            System.out.println(meta.toString());
            if(null != meta.getMid()){
                Meta m = metaRepository.findById(meta.getMid()).get();
                System.out.println("更新！！！！！");
//                metaRepository.save(m);
            }else{
//                metaRepository.save(meta);
                System.out.println("插入！！！！！");
            }
        }else{
            throw new InternalException(ErrorEnum.META_IS_EXIST);
        }
    }

    @Override
    public void saveMeta(Integer cid, String names, String type) {
        if(null==cid || StringUtils.isBlank(names) || StringUtils.isBlank(type))
            throw new InternalException(ErrorEnum.PARAM_IS_EMPTY);
        //按逗号分隔项目名
        String[] nameArr =StringUtils.split(names,",");

        //遍历项目名
        for (String name : nameArr) {

            int mid;
            Meta meta = metaRepository.findMetaByNameAndType(name,type);
            //若存在该项目，则直接获得id
            if(null!=meta){         //若存在该项目，则直接获得id
                mid=meta.getMid();
            }else{                  //若不存在，则新增再获得id
                Meta temp = new Meta();
                temp.setName(name);
                temp.setSlug(name);
                temp.setType(type);
                metaRepository.save(temp);
                mid = metaRepository.findLastId();
            }
            //存入关系表中
            if(mid!=0){
                relationRepository.save(new Relation(cid,mid));
            }

        }
    }
}
