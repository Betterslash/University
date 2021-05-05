package webubb.wplab7xbetterslash.mapper;

import org.mapstruct.Mapper;
import webubb.wplab7xbetterslash.dto.UserDto;
import webubb.wplab7xbetterslash.model.UserEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto fromEntitytoDto(UserEntity userEntity);

    UserEntity fromDtoToEntity(UserDto userDto);

    List<UserDto> fromEntitiesToDtos(List<UserEntity> userEntities);

    List<UserEntity> fromDtosToEntities(List<UserDto> userDtos);
}
