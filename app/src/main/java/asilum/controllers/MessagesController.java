package asilum.controllers;

import asilum.DTO.MessageDTO;
import asilum.exceptions.UserNotFoundException;
import asilum.models.message.Message;
import asilum.repositories.MessageRepository;
import asilum.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class MessagesController extends BaseController {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired // This means to get the bean called userRepository
    private UserRepository userRepository;

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
}
