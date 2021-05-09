package webubb.wplab7xbetterslash.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import webubb.wplab7xbetterslash.dto.TopicDto;
import webubb.wplab7xbetterslash.service.TopicService;

@RestController
@RequestMapping("/topic-entity")
@RequiredArgsConstructor
public class TopicRestController {

    private final TopicService topicService;

    @PostMapping("/add/{id}")
    public TopicDto saveTopic(@RequestBody  TopicDto topicDto, @PathVariable Long id){
        return this.topicService.saveTopic(topicDto, id).orElseThrow(() -> new RuntimeException("Couldn't save topic!"));
    }
}
