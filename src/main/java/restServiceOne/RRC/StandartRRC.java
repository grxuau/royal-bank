package restServiceOne.RRC;

import lombok.Getter;

@Getter
public class StandartRRC {

    private final int status;
    private final String description;
    private final Object data;

    public StandartRRC(int status, String description, Object data){
        this.status = status;
        this.description = description;
        this.data = data;

    }
    public StandartRRC(){
        this.status = 100;
        this.description = "User not found";
        this.data = null;
    }
    public StandartRRC(int status, String description){
        this.status = status;
        this.description = description;
        this.data = null;
    }

}


