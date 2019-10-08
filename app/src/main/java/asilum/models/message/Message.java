package asilum.models.message;

import asilum.models.user.User;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Component
@Entity
@Table(name="message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Size(min=1, max=255, message="Invalid length.")
    private String text;


    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinColumn(name="sender_id", nullable = false)
    @Valid
    private User sender;


    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinColumn(name="recipient_id", nullable = false)
    @Valid
    private User recipient;


    public Message(@NotNull String text, @Validated @NotNull User sender , @Validated @NotNull User recipient) {
        this.text = text;
        this.sender = sender;
        this.recipient = recipient;
    }
    public Message() {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Integer getId() {
        return id;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public String toString(){
        return "id : " + this.id + "\n" +
                "message : " + this.text + "\n" +
                "sender : " + this.sender + "\n";
    }

}
