package user;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
}
