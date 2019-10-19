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

import java.util.ArrayList;
import java.util.List;


@Controller
public class UserController extends BaseController {
    @Autowired // This means to get the bean called userRepository
    private UserRepository userRepository;

    @CrossOrigin
    @PostMapping(path="/users")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    UserDTO addUsers(@RequestBody @Validated User user){
        user.hashPassword();
        return new UserDTO(userRepository.save(user));
    }

    @CrossOrigin
    @GetMapping(path="/users")
    public @ResponseBody
    UserDTO getUser(@Validated Username username, @Validated String password) throws UserNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        if (user.getPassword().match(password)){
            return new UserDTO(user);
        } else {
            throw new UserNotFoundException();
        }
    }

    @CrossOrigin
    @GetMapping(path="/users/list")
    public @ResponseBody
    List<UserDTO> getAllUser() {
        List<UserDTO> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> users.add(new UserDTO(user)));
        return users;
    }



    @CrossOrigin
    @GetMapping(path="/users/count")
    public @ResponseBody
    UsersCount getUserCount() {
        // This returns a JSON or XML with the user
        return new UsersCount(userRepository.count());

    }

}