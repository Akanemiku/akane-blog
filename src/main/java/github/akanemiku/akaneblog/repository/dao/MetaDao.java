package github.akanemiku.akaneblog.repository.dao;

import github.akanemiku.akaneblog.dto.MetaDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * jpaRepository中，不能以DTO为返回值，会提示未知实体
 * 可通过两个语句查询最后合并成DTO，但效率过低
 * 故通过mybatis实现，pom引入，dao层添加注释及实现mapper.xml文件
 */
@Mapper
public interface MetaDao {
    /**
     * 根据sql查询
     * 查询某一type下的所有数据，根据输入排序，并限制条数
     *
     * @param parMap
     * @return
     */
    List<MetaDTO> selectFromSql(Map<String, Object> parMap);
}
