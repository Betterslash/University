package webubb.wplab7xbetterslash.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webubb.wplab7xbetterslash.service.TopicService;
import webubb.wplab7xbetterslash.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserLoggedController {

    private final UserService userService;

    private final TopicService topicService;

    @GetMapping("/logged-in")
    public String rootContext(@RequestParam String username,
                              @RequestParam Long id,
                              Model model){
        if(this.userService.getSession(id)
        .orElseThrow(() -> new RuntimeException("Something went wrong during redirecting!")) == 1) {
            model.addAttribute("username", username);
            model.addAttribute("topics", topicService.getAllTopics());
            return "index";
        }else{
            return "home";
        }
    }
}
