package github.akanemiku.akaneblog.repository;

import github.akanemiku.akaneblog.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option,String> {
}
