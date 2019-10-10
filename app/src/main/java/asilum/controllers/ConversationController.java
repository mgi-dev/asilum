package asilum.controllers;

import asilum.DTO.Conversation;
import asilum.services.ConversationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ConversationController {

    @GetMapping(path = "/conversation")
    public @ResponseBody
    List<Conversation> getConversation (@RequestParam Integer userId){
        ConversationService service = new ConversationService();
        return service.getConversations(userId);
    }

}