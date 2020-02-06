package github.akanemiku.akaneblog.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 项目表
 */
@Data
@Entity
@Table(name = "t_meta")
public class Meta implements Serializable {
    /**
     * 项目主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mid;
    /**
     * 名称
     */
    private String name;

    /**
     * 项目缩略名
     */
    private String slug;

    /**
     * 项目类型
     */
    private String type;

    /**
     * 对应的文章类型
     */
    private String contentType;

    /**
     * 选项描述
     */
    private String description;

    /**
     * 项目排序
     */
    private Integer sort;
}
