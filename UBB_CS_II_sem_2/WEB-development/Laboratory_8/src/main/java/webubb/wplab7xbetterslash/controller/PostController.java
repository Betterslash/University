package webubb.wplab7xbetterslash.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webubb.wplab7xbetterslash.dto.PostDto;
import webubb.wplab7xbetterslash.service.PostService;

import java.util.List;

@RequestMapping("/posts")
@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/save/{id}/{topic}")
    public PostDto savePost(@RequestBody PostDto postDto,
                            @PathVariable Long id,
                            @PathVariable Long topic){
        return this.postService.savePost(postDto, id, topic)
                .orElseThrow(() -> new RuntimeException("Something went wrong during saving the post!"));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody PostDto postDto) {
        try {
            this.postService.updatePost(id, postDto);
            return new ResponseEntity<>("Updated the post succesfully!", HttpStatus.OK);
        }catch (RuntimeException runtimeException){
            return new ResponseEntity<>(runtimeException.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id){
        try{
            this.postService.deletePost(id);
            return new ResponseEntity<>("Deleted succesfully!", HttpStatus.OK);
        }catch (RuntimeException runtimeException){
            return new ResponseEntity<>(runtimeException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<PostDto> getPosts(){
        return this.postService.getPosts();
    }
}
