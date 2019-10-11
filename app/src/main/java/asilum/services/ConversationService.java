package asilum.services;

import asilum.DTO.Conversation;
import asilum.models.message.Message;
import asilum.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ConversationService {
    @Autowired
    private MessageRepository messageRepository;

    public List<Conversation> getConversations(int userId) {

        List<Message> sentMessages = messageRepository.findBySenderId(userId);
        List<Message> receivedMessages = messageRepository.findByRecipientId(userId);
        List<Conversation> conversations = new ArrayList<>();

        for (Message message : sentMessages) {
            boolean ordered = false;
            for (Conversation conversation : conversations) {
                if (
                        conversation.containsSpeaker(message.getRecipient().getId())
                ) {
                    conversation.addMessage(message);
                    ordered = true;
                }
            }
            if (!ordered) {
                conversations.add(new Conversation());
                conversations.get(conversations.size() - 1).addMessage(message);
            }
        }
        for (Message message : receivedMessages) {
            boolean ordered = false;
            for (Conversation conversation : conversations) {
                if (conversation.containsSpeaker(message.getSender().getId())) {
                    conversation.addMessage(message);
                    ordered = true;
                    break;
                }
            }
            if (!ordered) {
                conversations.add(new Conversation());
                conversations.get(conversations.size() - 1).addMessage(message);
            }
        }
        for (Conversation conversation : conversations){
            Collections.sort(conversation.getMessages(), new Comparator<Message>() {
                public int compare(Message o1, Message o2) {
                    return o1.getCreatedAt().compareTo(o2.getCreatedAt());
                }
            });
        }
        return conversations;
    }
}
