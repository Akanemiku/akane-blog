package github.akanemiku.akaneblog.service.impl;

import github.akanemiku.akaneblog.constant.Types;
import github.akanemiku.akaneblog.model.Content;
import github.akanemiku.akaneblog.model.Meta;
import github.akanemiku.akaneblog.model.Relation;
import github.akanemiku.akaneblog.repository.ContentRepository;
import github.akanemiku.akaneblog.repository.MetaRepository;
import github.akanemiku.akaneblog.repository.RelationRepository;
import github.akanemiku.akaneblog.service.ContentService;
import github.akanemiku.akaneblog.service.MetaService;
import github.akanemiku.akaneblog.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    ContentRepository contentRepository;
    @Autowired
    RelationRepository relationRepository;
    @Autowired
    MetaService metaService;


    @Override
    public Page<Content> getAllArticles(Pageable pageable) {
        return contentRepository.findAll(pageable);
    }

    @Override
    public List<Content> getArticleByCategory(String category) {
        // TODO category为空异常
        return contentRepository.findArticleByCategory(category);
    }

    @Override
    public List<Content> getArticleByTag(Meta tag) {
        // TODO tag为空异常
        List<Relation> relationList = relationRepository.findRelationByMid(tag.getMid());
        if(null!=relationList&&relationList.size()>0){
            return contentRepository.findArticleByTags(Converter.RelationListToCidList(relationList));
        }
        return null;
    }

    @Override
    public Content getArticleById(Integer cid) {
        // TODO cid为空异常
        Optional<Content> content = contentRepository.findById(cid);
        return content.get();
    }

    @Override
    @Transactional
    public void updateContent(Content content) {
        contentRepository.save(content);
    }

    @Override
    @Transactional
    public void saveContent(Content content) {
        // TODO 异常判断
        //获取标签
        String tags = content.getTags();
        //获取分类
        String categories = content.getCategories();
        //保存文章
        contentRepository.save(content);
        // 添加分类和标签
        int cid;
        if(content.getCid()!=null){
            cid = content.getCid();
        }else{
            cid = contentRepository.findLastId();
        }
        if(tags!=null){
            metaService.saveMeta(cid, tags, Types.TAG.getType());
        }
        if(categories!=null){
            metaService.saveMeta(cid, categories, Types.CATEGORY.getType());
        }
    }

    @Override
    public Long getContentsCount() {
        return contentRepository.findContentsCount();
    }

    @Override
    public void deleteArticleById(Integer cid) {
        contentRepository.deleteById(cid);
    }
}
