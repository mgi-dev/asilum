package asilum.controllers;

import asilum.DTO.MessageDTO;
import asilum.DTO.UserDTO;
import asilum.exceptions.MessageNotFoundException;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MessagesController extends BaseController {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;

    @CrossOrigin
    @PostMapping(path = "/messages")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    MessageDTO addMessage(@RequestBody @Validated MessageDTO rawMessage) throws Exception {
        Message message = new Message(
                rawMessage.getText(),
                userRepository.findById(rawMessage.getSenderId()).orElseThrow(UserNotFoundException::new),
                userRepository.findById(rawMessage.getRecipientId()).orElseThrow(UserNotFoundException::new)
        );
        return new MessageDTO(messageRepository.save(message));
    }

    @CrossOrigin
    @GetMapping(path="/messages/{messageId}")
    public @ResponseBody
    MessageDTO getUser(@PathVariable(value="messageId") Integer messageId) throws MessageNotFoundException {
        return new MessageDTO(messageRepository.findById(messageId).orElseThrow(MessageNotFoundException::new));
    }
}

