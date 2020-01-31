package github.akanemiku.akaneblog.service.impl;

import github.akanemiku.akaneblog.model.Content;
import github.akanemiku.akaneblog.repository.ContentRepository;
import github.akanemiku.akaneblog.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    ContentRepository contentRepository;

    @Override
    public Page<Content> getAllArticles(Pageable pageable) {
        return contentRepository.findAll(pageable);
    }

    @Override
    public List<Content> getArticleByCategory(String category) {
        // TODO category为空异常
        return contentRepository.getArticleByCategory(category);
    }
}
