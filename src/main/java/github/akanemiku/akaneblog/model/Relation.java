package github.akanemiku.akaneblog.model;

import github.akanemiku.akaneblog.model.id.RelationId;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 文章关联信息表
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "t_relationship")
public class Relation implements Serializable {
    @EmbeddedId
    RelationId relationId;
}
