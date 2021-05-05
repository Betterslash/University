package webubb.wplab7xbetterslash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import webubb.wplab7xbetterslash.dto.TopicDto;
import webubb.wplab7xbetterslash.mapper.TopicMapper;
import webubb.wplab7xbetterslash.repository.TopicRepository;
import webubb.wplab7xbetterslash.repository.UserRepository;
import webubb.wplab7xbetterslash.service.TopicService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final UserRepository userRepository;

    private final TopicRepository topicRepository;

    private final TopicMapper topicMapper;

    @Override
    public Optional<TopicDto> saveTopic(TopicDto topicDto, Long id) {
        return Optional.of(this.topicMapper.fromEntityToDto(
                this.topicRepository.save(
                        this.topicMapper.fromDtoToEntity(topicDto, this.userRepository.getOne(id))
                )
        ));
    }

    @Override
    public List<TopicDto> getAllTopics() {
        return this.topicMapper.fromEntitiesToDtos(this.topicRepository.findAll());
    }

    @Override
    public Optional<TopicDto> getOne(Long id) {
        return Optional.of(this.topicMapper.fromEntityToDto(this.topicRepository.getOne(id)));
    }
}
