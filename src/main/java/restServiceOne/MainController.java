package restServiceOne;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import restServiceOne.BuisnesLogic.CommonLogic;
import restServiceOne.BuisnesLogic.UserLogic;
import restServiceOne.RRC.*;
import restServiceOne.hibernate.entity.UserEntity;

@RestController
public class MainController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public GreetingRRC greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new GreetingRRC(counter.incrementAndGet(), String.format(template, name));
    }
    @GetMapping("/user")
    public StandartRRC getUser
            (@RequestParam(value = "name", defaultValue = "") String name,
             @RequestParam(value = "email", defaultValue = "") String email,
             @RequestParam(value = "hashcode", defaultValue = "") String hashcode) {
        StandartRRC user;
        if (hashcode.equals("")){
            user = UserLogic.findUser(name,email);
        }else {
            user = UserLogic.getUser(name,email,hashcode);
        }
        return user;
    }
    @PostMapping(path = "user")
    public StandartRRC postUser(@RequestBody UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDTO,userEntity);

        if (CommonLogic.saveData(userEntity)) {
            userDTO.setToken(SecurityController.getToken(userEntity.getId()));
            return new StandartRRC(0, "", userDTO);
        } else {
            return new StandartRRC(900,"Some error on creating user");
        }
    }
}
