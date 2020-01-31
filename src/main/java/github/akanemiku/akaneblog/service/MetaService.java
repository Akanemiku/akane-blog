package github.akanemiku.akaneblog.service;

import github.akanemiku.akaneblog.dto.MetaDTO;
import github.akanemiku.akaneblog.model.Meta;

import java.util.List;

public interface MetaService {
    /**
     * 根据类型查询项目列表，附带内容数量
     * @param type      项目类型
     * @param orderBy   排序字段
     * @param limit     限制条数
     * @return
     */
    List<MetaDTO> getMetaList(String type, String orderBy, int limit);

    Long getMetasCountByType(String type);

    Meta getMetaByNameAndType(String type,String name);
}
