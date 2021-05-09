package webubb.wplab7xbetterslash.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class TopicDto {
    private Long id;

    private String subject;

    private Long user;

    List<PostDto> posts;
}
