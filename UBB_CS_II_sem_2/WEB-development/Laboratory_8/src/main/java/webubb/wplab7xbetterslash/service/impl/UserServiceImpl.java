package webubb.wplab7xbetterslash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import webubb.wplab7xbetterslash.dto.UserDto;
import webubb.wplab7xbetterslash.mapper.UserMapper;
import webubb.wplab7xbetterslash.model.UserEntity;
import webubb.wplab7xbetterslash.repository.UserRepository;
import webubb.wplab7xbetterslash.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;



    @Override
    public Optional<UserDto> login(UserDto userDto) {
        UserEntity responseEntity = this.userRepository.findByUsernameAndPassword(userDto.getUsername(), userDto.getPassword());
        if(responseEntity != null){
            responseEntity.setState(1);
            this.userRepository.save(responseEntity);
        }
        return Optional.of(this.userMapper.fromEntitytoDto(responseEntity));
    }

    @Override
    public Optional<Integer> getSession(Long id) {
        return Optional.of(this.userRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Something went wrong during user searching in db!")).getState());

    }

    @Override
    public Optional<UserDto> logout(Long id) {
        UserEntity responseEntity =
                this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Finding user went wrong!"));
        responseEntity.setState(0);
        this.userRepository.save(responseEntity);
        return Optional.of(this.userMapper.fromEntitytoDto(responseEntity));
    }
}
