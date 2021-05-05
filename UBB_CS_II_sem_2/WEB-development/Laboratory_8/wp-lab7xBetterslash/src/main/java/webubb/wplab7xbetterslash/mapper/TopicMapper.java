package webubb.wplab7xbetterslash.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import webubb.wplab7xbetterslash.dto.TopicDto;
import webubb.wplab7xbetterslash.model.TopicEntity;
import webubb.wplab7xbetterslash.model.UserEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, PostMapper.class})
public interface TopicMapper {
    default TopicEntity fromDtoToEntity(TopicDto topicDto, UserEntity userEntity){
        TopicEntity topicEntity = new TopicEntity();
        topicEntity.setId(topicDto.getId());
        topicEntity.setSubject(topicDto.getSubject());
        topicEntity.setUser(userEntity);
        return topicEntity;
    }

    @Mapping(target = "user", source = "user.id")
    TopicDto fromEntityToDto(TopicEntity topicEntity);

    List<TopicDto> fromEntitiesToDtos(List<TopicEntity> topicEntities);

    //List<TopicEntity> fromDtosToEntities(List<TopicDto> topicDtos);
}
