package restServiceOne.RRC;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String name;
    private String email;
    private String token;
    private String hashcode;

}
