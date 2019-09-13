package asilum.models;

import asilum.models.users.Password;
import asilum.models.users.Username;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Component
@Entity
@Table(name="user",
        uniqueConstraints = {@UniqueConstraint(columnNames={"username"})}
)
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Embedded
    @NotNull
    @Valid
    private Username username;

    @Embedded
    @NotNull
    @Valid
    private Password password;

    public User() {}

    public User(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public User(Username username, Password password) {
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
}