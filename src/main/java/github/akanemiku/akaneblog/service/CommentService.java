package github.akanemiku.akaneblog.service;

import github.akanemiku.akaneblog.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentsByCid(Integer cid);

    void insertComment(Comment comment);
}
