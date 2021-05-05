package webubb.wplab7xbetterslash.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webubb.wplab7xbetterslash.dto.PostDto;
import webubb.wplab7xbetterslash.service.PostService;

import java.util.List;

@RequestMapping("/posts")
@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public List<PostDto> getPosts(){
        return this.postService.getPosts();
    }
}
