package restServiceOne.RRC;

import lombok.Data;
import lombok.Getter;

@Getter
public class UserRRC {

    private final int status;
    private final String description;
    private final UserDAO user;

    public UserRRC(int status, String description, String name, String email, String token){
        this.status = status;
        this.description = description;
        this.user = new UserDAO(name,email,token);

    }
    public UserRRC(){
        this.status = 100;
        this.description = "User not found";
        this.user = null;
    }
    public UserRRC(int status,String description){
        this.status = status;
        this.description = description;
        this.user = null;
    }

    @Data
    private static class UserDAO{

        private final String name;
        private final String email;
        private final String token;

    }

}


