package webubb.wplab7xbetterslash.service;

import webubb.wplab7xbetterslash.dto.PostDto;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Optional<PostDto> savePost(PostDto postDto, Long id, Long topic);

    List<PostDto> getPosts();

    void updatePost(Long id, PostDto postDto);

    void deletePost(Long id);

}
