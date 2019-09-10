package asilum.controllers;

import asilum.exceptions.IncorrectUsernameOrPasswordException;
import asilum.models.User;
import asilum.repositories.UserRepository;
import asilum.models.users.UsersCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller    // This means that this class is a controllers
//@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class UserController extends BaseController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;

    @PostMapping(path="/users") // Map ONLY POST Requests
    public @ResponseBody
    String addNewUser (@RequestParam String name
            , @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User();
        n.setUsername(name);
        n.setPassword(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/users")
    public @ResponseBody User getUser(@RequestParam String name, @RequestParam String password) throws IncorrectUsernameOrPasswordException {
        // This returns a JSON or XML with the users
        System.out.println("ohhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        System.out.println(name);
        User user = userRepository.findByUsernameAndPassword(name, password);
        System.out.println(user);
        if (user == null){
            throw new IncorrectUsernameOrPasswordException();
        }
        return userRepository.findByUsernameAndPassword(name, password);
    }


    @GetMapping(path="/users/count")
    public @ResponseBody
    UsersCount getUserCount() {
        // This returns a JSON or XML with the users
        System.out.println("ohhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");

        return new UsersCount(userRepository.count());

    }

}