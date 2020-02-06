package github.akanemiku.akaneblog.repository;

import github.akanemiku.akaneblog.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    /**
     * 根据文章cid获取所有评论
     *
     * @param cid
     * @return
     */
    // TODO 评论状态判断
    @Query(nativeQuery = true,value = "select * from t_comment where status = 1 and cid = ?1")
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

    /**
     * 根据pageable分页查找全部评论，按时间倒序
     *
     * @param pageable
     * @return
     */
    @Query(nativeQuery = true, value = "select * from t_comment order by created desc")
    Page<Comment> findAll(Pageable pageable);

    /**
     * 更新评论状态
     *
     * @param coid
     * @param status
     */
    @Query(nativeQuery = true, value = "update t_comment set status = :status where coid = :coid")
    @Modifying
    void updateCommentStatus(@Param("coid") Integer coid, @Param("status") String status);

}
