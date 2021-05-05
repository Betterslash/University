package webubb.wplab7xbetterslash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webubb.wplab7xbetterslash.model.TopicEntity;

@Repository
public interface TopicRepository extends JpaRepository<TopicEntity, Long> {
}
