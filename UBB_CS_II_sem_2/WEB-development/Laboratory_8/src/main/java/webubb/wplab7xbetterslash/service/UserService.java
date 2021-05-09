package webubb.wplab7xbetterslash.service;

import webubb.wplab7xbetterslash.dto.UserDto;

import java.util.Optional;

public interface UserService {
    Optional<UserDto> login(UserDto userDto);

    Optional<Integer> getSession(Long id);

    Optional<UserDto> logout(Long id);
}
