package github.akanemiku.akaneblog.service;

import github.akanemiku.akaneblog.model.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContentService {
    Page<Content> getAllArticles(Pageable pageable);
}
