package webubb.wplab7xbetterslash.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostDto {

    private Long id;

    private String text;

    private Long user;

    private Long topic;
}
