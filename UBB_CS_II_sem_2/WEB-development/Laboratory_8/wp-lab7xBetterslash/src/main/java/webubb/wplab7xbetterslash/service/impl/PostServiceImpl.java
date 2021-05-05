package webubb.wplab7xbetterslash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import webubb.wplab7xbetterslash.dto.PostDto;
import webubb.wplab7xbetterslash.mapper.PostMapper;
import webubb.wplab7xbetterslash.repository.PostRepository;
import webubb.wplab7xbetterslash.service.PostService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final PostMapper postMapper;


    @Override
    public Optional<PostDto> savePost(PostDto postDto) {
        return Optional.empty();
    }

    @Override
    public List<PostDto> getPosts() {
        return this.postMapper.fromEntitiesToDtos(this.postRepository.findAll());
    }
}
