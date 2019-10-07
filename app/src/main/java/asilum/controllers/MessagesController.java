package asilum.controllers;

import asilum.exceptions.UserNotFoundException;
import asilum.models.message.Message;
import asilum.models.user.User;
import asilum.models.user.Username;
import asilum.repositories.MessageRepository;
import asilum.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class MessagesController extends BaseController {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired // This means to get the bean called userRepository
    private UserRepository userRepository;

    @PostMapping(path = "/messages")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Message addMessage(@Validated Message message, @Validated Username username) throws UserNotFoundException {
        System.out.println("ah. C'est nouveau ca!");
        System.out.println(username);
        System.out.println(username.getUsername());
        System.out.println(username.getUsername().toString());
        message.setSender(userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new));
        return messageRepository.save(message);
    }

}
