package github.akanemiku.akaneblog.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 网站配置项
 */
@Data
@Entity
@Table(name = "t_option")
public class Option implements Serializable {
    /** 名称 */
    @Id
    private String name;
    /** 内容 */
    private String value;
    /** 描述 */
    private String description;
}
