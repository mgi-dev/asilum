package asilum.controllers;


import asilum.DTO.UserDTO;
import asilum.exceptions.UserNotFoundException;
import asilum.models.user.User;
import asilum.models.user.Username;
import asilum.repositories.UserRepository;
import asilum.models.user.UsersCount;
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
    public @ResponseBody
    UserDTO getUser(Username username, String password) throws UserNotFoundException {

        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        if (user.getPassword().match(password)){
//            return user.mappingDTO();
            return new UserDTO(user);
        } else {
            throw new UserNotFoundException();
        }
    }


    @GetMapping(path="/users/count")
    public @ResponseBody
    UsersCount getUserCount() {
        // This returns a JSON or XML with the user
        return new UsersCount(userRepository.count());

    }

}