package github.akanemiku.akaneblog.service;

import github.akanemiku.akaneblog.dto.MetaDTO;
import github.akanemiku.akaneblog.model.Meta;

import java.util.List;

public interface MetaService {
    /**
     * 根据类型查询项目列表，附带内容数量
     *
     * @param type    项目类型
     * @param orderBy 排序字段
     * @param limit   限制条数
     * @return
     */
    List<MetaDTO> getMetaList(String type, String orderBy, int limit);

    /**
     * 获得所有项目
     *
     * @return
     */
    List<Meta> getMetasByType(String type);

    /**
     * 根据类型获取数目
     *
     * @param type
     * @return
     */
    Long getMetasCountByType(String type);

    /**
     * 根据类型和名称获取对应项
     *
     * @param type
     * @param name
     * @return
     */
    Meta getMetaByNameAndType(String type, String name);

    /**
     * 添加或修改项目
     * @param meta
     */
    void saveMeta(Meta meta);

    /**
     * 用于文章发布时，批量添加或修改项目
     * @param cid
     * @param names
     * @param type
     */
    void saveMeta(Integer cid, String names, String type);

    /**
     * 根据id删除项目，且连带删除所属文章
     * @param mid
     */
    void deleteById(Integer mid);
}
