package webubb.wplab7xbetterslash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webubb.wplab7xbetterslash.dto.PostDto;
import webubb.wplab7xbetterslash.mapper.PostMapper;
import webubb.wplab7xbetterslash.model.PostEntity;
import webubb.wplab7xbetterslash.repository.PostRepository;
import webubb.wplab7xbetterslash.repository.TopicRepository;
import webubb.wplab7xbetterslash.repository.UserRepository;
import webubb.wplab7xbetterslash.service.PostService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final PostMapper postMapper;

    private final UserRepository userRepository;

    private final TopicRepository topicRepository;

    @Override
    public Optional<PostDto> savePost(PostDto postDto, Long id, Long topic) {
        return Optional.of(this.postMapper.fromEntityToDto(
                this.postRepository.save(this.postMapper.fromDtoToEntity(postDto,
                        this.userRepository.getOne(id),
                        this.topicRepository.getOne(topic)))
        ));
    }

    @Override
    public List<PostDto> getPosts() {
        return this.postMapper.fromEntitiesToDtos(this.postRepository.findAll());
    }

    @Override
    @Transactional
    public void updatePost(Long id, PostDto postDto) {
        Optional<PostEntity> postEntity = this.postRepository.findById(id);
        Long user = postDto.getUser();
        Long topic = postDto.getTopic();
        postEntity.ifPresent(e -> this.postRepository.save(
                this.postMapper.fromDtoToEntity(postDto, userRepository.getOne(user), topicRepository.getOne(topic))
        ));

    }

    @Override
    public void deletePost(Long id) {
        this.postRepository.deleteById(id);
    }
}
