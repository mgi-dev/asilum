package asilum.controllers;

import asilum.DTO.Conversation;
import asilum.DTO.ConversationDTO;
import asilum.services.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@Controller
public class ConversationController extends BaseController {

    @Autowired
    private ConversationService conversationService;

    @CrossOrigin
    @GetMapping(path="/conversations")
    public @ResponseBody
    List<ConversationDTO> getConversation (@RequestParam Integer userId){
        List<ConversationDTO> conversations = new ArrayList<ConversationDTO>();
        for (Conversation conversation : conversationService.getConversations(userId)){
            conversations.add(new ConversationDTO(conversation));
        }
        return conversations;
    }
}