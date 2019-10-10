package asilum.DTO;

import asilum.models.message.Message;

import java.util.ArrayList;
import java.util.List;

public class ConversationDTO {
    private List<MessageDTO> messages;

    public ConversationDTO(List<MessageDTO> messages) {

        this.messages = new ArrayList<>();
        for (Message message : messages){

        }
    }

    public List<MessageDTO> getMessages() {
        return messages;
    }
}
