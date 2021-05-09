package webubb.wplab7xbetterslash.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private int state;

    @OneToMany(mappedBy = "user")
    private List<TopicEntity> topics;

    @OneToMany(mappedBy = "user")
    private List<PostEntity> posts;
}
