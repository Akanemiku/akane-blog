package github.akanemiku.akaneblog.service.impl;

import github.akanemiku.akaneblog.constant.Types;
import github.akanemiku.akaneblog.dto.StatisticsDTO;
import github.akanemiku.akaneblog.model.Comment;
import github.akanemiku.akaneblog.model.Content;
import github.akanemiku.akaneblog.repository.CommentRepository;
import github.akanemiku.akaneblog.repository.ContentRepository;
import github.akanemiku.akaneblog.repository.MetaRepository;
import github.akanemiku.akaneblog.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitorServiceImpl implements MonitorService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private MetaRepository metaRepository;


    @Override
    public StatisticsDTO getStatistics() {
        // 文章总数
        Long articles = contentRepository.findContentsCount();
        // 评论总数
        Long comments = commentRepository.findContentsCount();
        // 链接数
        Long links = metaRepository.findMetasCountByType(Types.LINK.getType());
        StatisticsDTO dto = new StatisticsDTO();
        dto.setArticles(articles);
        dto.setComments(comments);
        dto.setLinks(links);
        return dto;
    }

    @Override
    public List<Content> getNewArticles(Integer limit) {
        if (limit < 0 || limit > 10) {
            limit = 10;
        }
        return contentRepository.findAllByLimit(limit);
    }

    @Override
    public List<Comment> getNewComment(Integer limit) {
        if (limit < 0 || limit > 10) {
            limit = 10;
        }
        return commentRepository.findAllByLimit(limit);
    }
}
