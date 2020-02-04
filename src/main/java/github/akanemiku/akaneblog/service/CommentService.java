package github.akanemiku.akaneblog.service;

import github.akanemiku.akaneblog.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {
    /**
     * 根据文章id获得所有评论
     * @param cid
     * @return
     */
    List<Comment> getCommentsByCid(Integer cid);

    /**
     * 添加评论
     * @param comment
     */
    void insertComment(Comment comment);

    /**
     * 根据pageable分页
     * @param pageable
     * @return
     */
    Page<Comment> getAllComments(Pageable pageable);

    /**
     * 根据评论id获得相应评论
     * @param id
     * @return
     */
    Comment getCommentById(Integer id);

    /**
     * 更新评论状态
     * @param coid
     * @param status
     */
    void updateComment(Integer coid, String status);
}
