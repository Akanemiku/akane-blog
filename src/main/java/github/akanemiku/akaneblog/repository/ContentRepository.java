package github.akanemiku.akaneblog.repository;

import github.akanemiku.akaneblog.model.Content;
import github.akanemiku.akaneblog.model.Relation;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Integer> {

    /**
     * 根据pageable分页
     *
     * @param pageable
     * @return
     */
    @Query(nativeQuery = true, value = "select * from t_content order by created desc")
    Page<Content> findAll(Pageable pageable);

    /**
     * 通过分类获取文章
     *
     * @param category
     * @return
     */
    @Query(nativeQuery = true, value = "select * from t_content where categories=?1")
    List<Content> findArticleByCategory(@Param("category") String category);

    /**
     * 通过文章id列表获取所有文章
     *
     * @param cid
     * @return
     */
    @Query(nativeQuery = true, value = "select * from t_content where cid in :cid")
    List<Content> findArticleByTags(@Param("cid") List<Integer> cid);

    /**
     * 获取文章总数
     *
     * @return
     */
    @Query(nativeQuery = true, value = "select count(*) from t_content")
    Long findContentsCount();

    /**
     * 获得指定数目文章，按时间倒序
     *
     * @param limit
     * @return
     */
    @Query(nativeQuery = true, value = "select * from t_content order by created desc limit 0,?1")
    List<Content> findAllByLimit(@Param("limit") Integer limit);

    /**
     * 获得最新插入的数据id
     *
     * @return
     */
    @Query(nativeQuery = true, value = "select cid from t_content order by cid desc limit 1")
    Integer findLastId();
}
