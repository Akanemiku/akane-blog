package github.akanemiku.akaneblog.service.impl;

import github.akanemiku.akaneblog.constant.Types;
import github.akanemiku.akaneblog.constant.WebConst;
import github.akanemiku.akaneblog.dto.MetaDTO;
import github.akanemiku.akaneblog.enums.ErrorEnum;
import github.akanemiku.akaneblog.exception.InternalException;
import github.akanemiku.akaneblog.model.Content;
import github.akanemiku.akaneblog.model.Meta;
import github.akanemiku.akaneblog.model.Relation;
import github.akanemiku.akaneblog.repository.MetaRepository;
import github.akanemiku.akaneblog.repository.RelationRepository;
import github.akanemiku.akaneblog.repository.dao.MetaDao;
import github.akanemiku.akaneblog.service.ContentService;
import github.akanemiku.akaneblog.service.MetaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    @Autowired
    private ContentService contentService;

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
        if(StringUtils.isBlank(name)||StringUtils.isBlank(type))
            throw new InternalException(ErrorEnum.PARAM_IS_EMPTY);
        return metaRepository.findMetaByNameAndType(name,type);
    }

    @Override
    @Transactional
    public void saveMeta(Meta meta) {
        if(meta==null)
            throw new InternalException(ErrorEnum.PARAM_IS_EMPTY);
        // 通过项目名和类型查找有没有存在的
        Meta temp = metaRepository.findMetaByNameAndType(meta.getName(),meta.getType());
        // 判断是否找到有相同的
        if(null == temp){
            // 如果有mid则为更新
//            System.out.println(meta.toString());
            if(null != meta.getMid()){
                Meta m = metaRepository.findById(meta.getMid()).get();
                temp.setMid(m.getMid());
//                System.out.println("更新！！！！！");
                metaRepository.save(temp);
            }else{
                metaRepository.save(meta);
//                System.out.println("插入！！！！！");
            }
        }else{
            throw new InternalException(ErrorEnum.META_IS_EXIST);
        }
    }

    @Override
    @Transactional
    public void saveLink(Meta meta) {
        if(meta==null)
            throw new InternalException(ErrorEnum.PARAM_IS_EMPTY);
        metaRepository.save(meta);
    }

    @Override
    @Transactional
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

    @Override
    @Transactional
    public void deleteById(Integer mid) {
        if(null==mid)
            throw new InternalException(ErrorEnum.PARAM_IS_EMPTY);
        // 通过id找到对应项目
        Meta meta = metaRepository.findById(mid).get();
        // 获得项目名称和类型
        String type = meta.getType();
        String name = meta.getName();
        // 删除对应项目
        metaRepository.deleteById(mid);
        // 通过项目id找到关联表中的所有关联文章id
        List<Relation> relationList = relationRepository.findRelationByMid(mid);
        // 判断是否找到项目
        if(relationList!=null && relationList.size()>0){
            // 遍历项目id对应的所有文章
            for (Relation relation : relationList) {
                //获得当前文章
                Content article = contentService.getArticleById(relation.getCid());
                // 判断是否找到文章
                if(null != article){
                    // 若为分类类型
                    if(type.equals(Types.CATEGORY.getType())){
                        // 清除文章被删除的分类
                        String str = reMeta(name,article.getCategories());
                        article.setCategories(str);
                    }
                    // 若为标签类型
                    if(type.equals(Types.TAG.getType())){
                        // 清除文章被删除的标签
                        String str = reMeta(name,article.getTags());
                        article.setTags(str);
                    }
                    // 更新文章
                    contentService.updateContent(article);
                }
            }
        }
        //删除关联表中的数据
        relationRepository.deleteByMid(mid);
    }

    /**
     * 清除文章中已被删除的项目
     * e.g. name = "tag2",metas = "tag1,tag2,tag3" => metas = "tag1,tag3"
     * @param name
     * @param metas
     * @return
     */
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
}
