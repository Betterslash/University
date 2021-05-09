package webubb.wplab7xbetterslash.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webubb.wplab7xbetterslash.dto.TopicDto;
import webubb.wplab7xbetterslash.service.TopicService;
import webubb.wplab7xbetterslash.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/topics")
public class TopicController {

    private final TopicService topicService;

    private final UserService userService;

    @GetMapping
    public List<TopicDto> getTopics(){
        return this.topicService.getAllTopics();
    }

    @GetMapping("/{id}")
    public String getTopic(@PathVariable Long id,
                           @RequestParam Long userId,
                           Model model){
        TopicDto topic =  this.topicService.getOne(id)
                .orElseThrow(()-> new RuntimeException("Something went wrong during getiing the topic!"));
        model.addAttribute("topic", topic.getSubject());
        model.addAttribute("posts", topic.getPosts());
        model.addAttribute("text", topic.getSubject());
        model.addAttribute("currentTopicId", topic.getId());
        if(this.userService.getSession(userId).orElseThrow(() -> new RuntimeException("Something went wrong !")) == 1)
            return "topics";
        else
            throw new RuntimeException("Please login before tring to acces topics!");
    }
}
