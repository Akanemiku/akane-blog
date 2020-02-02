package github.akanemiku.akaneblog.service.impl;

import github.akanemiku.akaneblog.enums.CommentEnum;
import github.akanemiku.akaneblog.model.Comment;
import github.akanemiku.akaneblog.repository.CommentRepository;
import github.akanemiku.akaneblog.service.CommentService;
import github.akanemiku.akaneblog.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> getCommentsByCid(Integer cid) {
        // TODO cid为空异常
        return commentRepository.findAllByCid(cid);
    }

    @Override
    public void insertComment(Comment comment) {

        // TODO 内容判断
        // TODO 可设置作者，需要后端管理联动
        comment.setStatus(CommentEnum.STATUS_BLANK.getType());
        comment.setCreated(DateUtil.getCurrentUnixTime());
        commentRepository.save(comment);
        // TODO setCommentsNum
    }
}
