package restServiceOne.RRC;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDAO {

    private String name;
    private String email;
    private String token;

}
