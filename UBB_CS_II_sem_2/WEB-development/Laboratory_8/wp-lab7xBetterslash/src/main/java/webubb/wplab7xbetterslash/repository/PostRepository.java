package webubb.wplab7xbetterslash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webubb.wplab7xbetterslash.model.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
