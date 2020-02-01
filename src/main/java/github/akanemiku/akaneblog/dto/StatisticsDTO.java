package github.akanemiku.akaneblog.dto;

import lombok.Data;

@Data
public class StatisticsDTO {
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
