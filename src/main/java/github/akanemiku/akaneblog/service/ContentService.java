package github.akanemiku.akaneblog.service;

import github.akanemiku.akaneblog.model.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContentService {
    Page<Content> getAllArticles(Pageable pageable);

    List<Content> getArticleByCategory(String category);
}
