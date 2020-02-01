package github.akanemiku.akaneblog.repository;

import github.akanemiku.akaneblog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    /**
     * 根据文章cid获取所有评论
     *
     * @param cid
     * @return
     */
    // TODO 评论状态判断
    List<Comment> findAllByCid(Integer cid);

    /**
     * 获得评论总数
     *
     * @return
     */
    @Query(nativeQuery = true, value = "select count(*) from t_content")
    Long findContentsCount();

    /**
     * 获得指定数目评论，按时间倒序
     *
     * @param limit
     * @return
     */
    @Query(nativeQuery = true, value = "select * from t_comment order by created desc limit 0,?1")
    List<Comment> findAllByLimit(@Param("limit") Integer limit);
}
