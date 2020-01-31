package github.akanemiku.akaneblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 文章关联信息表
 */
@Data
@Entity
@Table(name = "t_relation")
@NoArgsConstructor
@AllArgsConstructor
@IdClass(Relation.class)//所有字段均为主键
public class Relation implements Serializable {
    /** 文章主键 */
    private Integer cid;

    /** 项目编号 */
    @Id
    private Integer mid;
}
