package github.akanemiku.akaneblog.repository;

import github.akanemiku.akaneblog.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LogRepository extends JpaRepository<Log, Integer> {
    /**
     * 获得指定数目日志，根据id倒序
     *
     * @return
     */
    @Query(nativeQuery = true, value = "select * from t_log order by id desc limit 0,?1")
    List<Log> findAllByLimit(@Param("limit") Integer limit);
}
