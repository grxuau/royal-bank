package restServiceOne;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import restServiceOne.BuisnesLogic.UserLogic;
import restServiceOne.RRC.*;

@RestController
public class MainController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public GreetingRRC greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new GreetingRRC(counter.incrementAndGet(), String.format(template, name));
    }
    @GetMapping("/user")
    public UserRRC getUser
            (@RequestParam(value = "name", defaultValue = "") String name,
             @RequestParam(value = "email", defaultValue = "") String email,
             @RequestParam(value = "hashcode", defaultValue = "") String hashcode) {
        UserRRC user;
        if (hashcode.equals("")){
            user = UserLogic.findUser(name,email);
        }else {
            user = UserLogic.getUser(name,email,hashcode);
        }
        return user;
    }
}