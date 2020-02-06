package github.akanemiku.akaneblog.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class StatisticsDTO implements Serializable {
    private static final long serialVersionUID = -3413718462427034976L;
    /**
     * 文章数
     */
    private Long articles;

    /**
     * 评论数
     */
    private Long comments;

    /**
     * 链接数
     */
    private Long links;
}
