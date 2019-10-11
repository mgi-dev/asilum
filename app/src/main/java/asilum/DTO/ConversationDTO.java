package asilum.DTO;

import asilum.models.message.Message;

import java.util.ArrayList;
import java.util.List;

public class ConversationDTO {
    private List<MessageDTO> messages;


    public ConversationDTO(Conversation conversation) {
        this.messages = new ArrayList<>();
        for (Message message : conversation.getMessages()){
            this.messages.add(new MessageDTO(message));
        }
    }

    public List<MessageDTO> getMessages() {
        return messages;
    }
}
