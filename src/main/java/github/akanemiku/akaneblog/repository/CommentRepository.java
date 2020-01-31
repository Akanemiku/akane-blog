package github.akanemiku.akaneblog.repository;

import github.akanemiku.akaneblog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    /**
     * 根据文章cid获取所有评论
     * @param cid
     * @return
     */
    // TODO 评论状态判断
    List<Comment> findAllByCid(Integer cid);
}
