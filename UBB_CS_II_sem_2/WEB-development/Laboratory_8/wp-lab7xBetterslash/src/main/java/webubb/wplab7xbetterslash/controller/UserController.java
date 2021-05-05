package webubb.wplab7xbetterslash.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webubb.wplab7xbetterslash.dto.UserDto;
import webubb.wplab7xbetterslash.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/logout/{id}")
    public ResponseEntity<?> logout(@PathVariable Long id){
        this.userService.logout(id)
                .orElseThrow(() -> new RuntimeException("There was an error in user service!"));
        return new ResponseEntity<>("Logged out!", HttpStatus.OK);
    }

    @PostMapping("/auth")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {

        try {
            UserDto response = this.userService.login(userDto)
                    .orElseThrow(() -> new RuntimeException("Login Failed!"));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (RuntimeException runtimeException){
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

}
