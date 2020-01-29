package github.akanemiku.akaneblog.model.id;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
public class RelationId implements Serializable {
    /** 文章主键 */
    private Integer cid;

    /** 项目编号 */
    private Integer mid;
}
