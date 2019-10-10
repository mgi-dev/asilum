package asilum.DTO;

import asilum.models.message.Message;

import java.util.ArrayList;
import java.util.List;

public class Conversation {
    private List<Message> messages;

    public Conversation() {
        this.messages = new ArrayList<Message>();
    }

//    public Conversation(ArrayList<Message> messages) {
//        this.messages = messages;
//    }

    public boolean containsSpeaker(int speakerId){
        if (!this.messages.isEmpty()){
            for (Message message : messages){
                if (message.getSender().getId() == speakerId || message.getRecipient().getId() == speakerId){
                    return true;
                }
            }
        }
        return false;
    }

    public void addMessage(Message message){
        this.messages.add(message);
    }

    public List<Message> getMessages(){
        return this.messages;
    }
}
