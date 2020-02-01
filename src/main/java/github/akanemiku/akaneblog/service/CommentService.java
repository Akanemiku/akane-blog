package github.akanemiku.akaneblog.service;

import github.akanemiku.akaneblog.model.Comment;

import java.util.List;

public interface CommentService {
    /**
     * 根据评论id获得所有评论
     * @param cid
     * @return
     */
    List<Comment> getCommentsByCid(Integer cid);

    /**
     * 添加评论
     * @param comment
     */
    void insertComment(Comment comment);
}
