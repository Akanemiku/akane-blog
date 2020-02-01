package github.akanemiku.akaneblog.service;

import github.akanemiku.akaneblog.dto.StatisticsDTO;
import github.akanemiku.akaneblog.model.Comment;
import github.akanemiku.akaneblog.model.Content;

import java.util.List;

public interface MonitorService {
    /**
     * 获取文章、评论、链接统计数据
     * @return
     */
    StatisticsDTO getStatistics();

    List<Content> getNewArticles(Integer limit);

    List<Comment> getNewComment(Integer limit);
}
