package github.akanemiku.akaneblog.dto;

import github.akanemiku.akaneblog.model.Meta;
import lombok.Data;

import java.io.Serializable;

/**
 * 标签、分类列表
 */
@Data
public class MetaDTO extends Meta implements Serializable {
    /**
     * 总数
     */
    private int count;
}
