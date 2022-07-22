package restServiceOne;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import restServiceOne.BuisnesLogic.CommonLogic;
import restServiceOne.BuisnesLogic.UserLogic;
import restServiceOne.Exceptions.ItemNotFoundException;
import restServiceOne.RRC.*;
import restServiceOne.hibernate.entity.UserEntity;

@RestController
public class MainController {
    @GetMapping("/test")
    public String getTest() {
        return "Server online";
    }

    @GetMapping("/user")
    public StandartRRC getUser
            (@RequestParam(value = "name", defaultValue = "") String name,
             @RequestParam(value = "email", defaultValue = "") String email,
             @RequestParam(value = "hashcode", defaultValue = "") String hashcode) {
        StandartRRC user;
        if (hashcode.equals("")) {
            user = UserLogic.findUser(name, email);
        } else {
            user = UserLogic.getUser(name, email, hashcode);
        }
        return user;
    }

    @PostMapping(path = "user")
    public StandartRRC postUser(@RequestBody UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDTO, userEntity);

        try {
            if (CommonLogic.saveData(userEntity)) {
                userDTO.setToken(SecurityController.getToken(userEntity.getId()));
                return new StandartRRC(0, "", userDTO);
            }
        } catch (Exception e) {
            return new StandartRRC(900, e.getMessage());
        }
        return null;
    }

    @PutMapping(path = "user")
    public StandartRRC putUser(@RequestParam(name = "token") String token, @RequestBody UserDTO userDTO) {

        int id;

        try {
            id = SecurityController.getID(token);
        } catch (ItemNotFoundException e) {
            return new StandartRRC(900, "Active user with token not found");
        }

        UserEntity userEntity = UserLogic.getUserByID(id).orElse(null);

        BeanUtils.copyProperties(userDTO, userEntity);

        try {
            if (CommonLogic.saveData(userEntity)) {
                userDTO.setToken(SecurityController.getToken(userEntity.getId()));
                return new StandartRRC(0, "", userDTO);
            }
        } catch (Exception e) {
            return new StandartRRC(900, e.getMessage());
        }
        return null;
    }
}
