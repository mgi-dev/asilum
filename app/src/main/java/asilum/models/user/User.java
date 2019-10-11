package asilum.models.user;

import asilum.models.database.ConstraintNames;
import asilum.models.message.Message;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Component
@Entity
@Table(name="user",
        uniqueConstraints = {@UniqueConstraint(name= ConstraintNames.UNIQUE_USERNAME_CONSTRAINT, columnNames={"username"})}
)
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)

    private Integer id;

    @OneToMany(mappedBy = "sender")
    private Set<Message> messages;

    @Embedded
    @NotNull
    @Valid
    private Username username;

    @Embedded
    @NotNull
    @Valid
    private Password password;

    @Nullable
    private String photoUri;


    public User() {
    }

    public User(String username, String password, String photoUri) {
        this.photoUri = photoUri;
        this.setUsername(username);
        this.setPassword(password);
    }

    public User(Username username, Password password, String photoUri) {
        this.photoUri = photoUri;
        this.setUsername(username);
        this.setPassword(password);
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Username getUsername() {
        return username;
    }

    public void setUsername(Username username) {
        this.username = username;
    }

    public void setUsername(String username) {
        this.username = new Username(username);
    }

    public Password getPassword() {
        return this.password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public void setPassword(String password){
        this.password = new Password(password);
    }

    public void hashPassword(){
        this.password.selfHash();
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }
}