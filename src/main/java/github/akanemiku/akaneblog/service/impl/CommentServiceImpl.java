package github.akanemiku.akaneblog.service.impl;

import github.akanemiku.akaneblog.enums.CommentEnum;
import github.akanemiku.akaneblog.enums.ErrorEnum;
import github.akanemiku.akaneblog.exception.InternalException;
import github.akanemiku.akaneblog.model.Comment;
import github.akanemiku.akaneblog.model.Content;
import github.akanemiku.akaneblog.repository.CommentRepository;
import github.akanemiku.akaneblog.repository.ContentRepository;
import github.akanemiku.akaneblog.service.CommentService;
import github.akanemiku.akaneblog.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ContentRepository contentRepository;

    @Override
    @Cacheable(value = "comment", key = "'commentsByCId_'+#p0")
    public List<Comment> getCommentsByCid(Integer cid) {
        if(cid==null)
            throw new InternalException(ErrorEnum.PARAM_IS_EMPTY);
        return commentRepository.findAllByCid(cid);
    }

    @Override
    @CacheEvict(value = "comment", allEntries = true)
    public void insertComment(Comment comment) {
        if(comment==null)
            throw new InternalException(ErrorEnum.PARAM_IS_EMPTY);
        // TODO 可设置作者，需要后端管理联动
        // 插入评论
        comment.setStatus(CommentEnum.UNCHECKED.getType());
        comment.setCreated(DateUtil.getCurrentUnixTime());
        commentRepository.save(comment);
        // 文章评论总数+1
        try{
            Content article = contentRepository.findById(comment.getCid()).get();
            article.setCommentsNum(article.getCommentsNum()+1);
            contentRepository.save(article);
        }catch (InternalException e){
            throw new InternalException(ErrorEnum.ARTICLE_IS_NULL);
        }
    }

    @Override
    @CacheEvict(value = "comment", allEntries = true, beforeInvocation = true)
    public Page<Comment> getAllComments(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    @Override
    @Cacheable(value = "comment", key = "'commentsById_'+#p0")
    public Comment getCommentById(Integer id) {
        return commentRepository.findById(id).get();
    }

    @Override
    @Transactional
    @CacheEvict(value = "comment", allEntries = true)
    public void updateComment(Integer coid, String status) {
        if (null == coid)
            throw new InternalException(ErrorEnum.PARAM_IS_EMPTY);
        commentRepository.updateCommentStatus(coid, status);
    }
}
