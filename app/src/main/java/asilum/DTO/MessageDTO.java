package asilum.DTO;

import asilum.models.message.Message;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MessageDTO {

    @NotNull
    private Integer senderId;
    @NotNull
    private Integer recipientId;
    @NotNull
    @Size(min=1, max=255, message="Invalid length.")
    private String text;

    public MessageDTO(Integer senderId, Integer recepientId, String text) {
        this.senderId = senderId;
        this.recipientId = recepientId;
        this.text = text;
    }

    public MessageDTO(Message message) {
        this.senderId = message.getSender().getId();
        this.recipientId = message.getRecipient().getId();
        this.text = message.getText();
    }


    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Integer recipientId) {
        this.recipientId = recipientId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
