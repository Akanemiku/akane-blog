package github.akanemiku.akaneblog.dto;

import github.akanemiku.akaneblog.model.Meta;
import lombok.Data;

import java.io.Serializable;

/**
 * 标签、分类列表
 */
@Data
public class MetaDTO extends Meta implements Serializable {
    private static final long serialVersionUID = 3470832499655710251L;
    /**
     * 总数
     */
    private int count;
}
