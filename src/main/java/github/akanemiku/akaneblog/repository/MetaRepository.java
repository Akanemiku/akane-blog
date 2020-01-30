package github.akanemiku.akaneblog.repository;

import github.akanemiku.akaneblog.model.Meta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MetaRepository extends JpaRepository<Meta, Integer> {
    /**
     * 查找某一类型的个数
     *
     * @param type
     * @return
     */
    @Query(nativeQuery = true, value = "select count(*) from t_meta where type like %?1%")
    Long getMetasCountByType(@Param("type") String type);
}
