package webubb.wplab7xbetterslash.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import webubb.wplab7xbetterslash.dto.PostDto;
import webubb.wplab7xbetterslash.model.PostEntity;
import webubb.wplab7xbetterslash.model.UserEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TopicMapper.class, UserMapper.class})
public interface PostMapper {

    default PostEntity fromDtoToEntity(PostDto postDto, UserEntity userEntity){
        PostEntity postEntity = new PostEntity();
        postEntity.setId(postDto.getId());
        postEntity.setText(postDto.getText());
        postEntity.setUser(userEntity);
        return postEntity;
    }

    @Mapping(target = "user", source = "user.id")
    PostDto fromEntityToDto(PostEntity postEntity);

    List<PostDto> fromEntitiesToDtos(List<PostEntity> postEntities);

    //List<PostEntity> fromDtosToEntities(List<PostDto> postDtos);
}
