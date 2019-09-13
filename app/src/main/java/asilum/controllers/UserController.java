package asilum.controllers;


import asilum.exceptions.UserNotFoundException;
import asilum.models.User;
import asilum.models.users.Username;
import asilum.repositories.UserRepository;
import asilum.models.users.UsersCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController extends BaseController {
    @Autowired // This means to get the bean called userRepository
    private UserRepository userRepository;


    @PostMapping(path="/users")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody User addUsers(@RequestBody @Validated User user){
        user.hashPassword();
        return userRepository.save(user);
    }


    @GetMapping(path="/users")
    public @ResponseBody User getUser(Username username, String password) throws UserNotFoundException {

        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        if (user.getPassword().match(password)){
            return user;
        } else {
            throw new UserNotFoundException();
        }
    }


    @GetMapping(path="/users/count")
    public @ResponseBody
    UsersCount getUserCount() {
        // This returns a JSON or XML with the users
        return new UsersCount(userRepository.count());

    }

}