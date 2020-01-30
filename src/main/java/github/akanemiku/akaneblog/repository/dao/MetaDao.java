package github.akanemiku.akaneblog.repository.dao;

import github.akanemiku.akaneblog.dto.MetaDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MetaDao {
    /**
     * 根据sql查询
     * @param parMap
     * @return
     */
    List<MetaDTO> selectFromSql(Map<String, Object> parMap);
}
