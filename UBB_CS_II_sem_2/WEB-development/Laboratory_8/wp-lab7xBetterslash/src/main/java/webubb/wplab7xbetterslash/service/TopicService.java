package webubb.wplab7xbetterslash.service;


import webubb.wplab7xbetterslash.dto.TopicDto;

import java.util.List;
import java.util.Optional;

public interface TopicService {
    Optional<TopicDto> saveTopic(TopicDto topicDto, Long id);
    List<TopicDto> getAllTopics();

    Optional<TopicDto> getOne(Long id);
}
