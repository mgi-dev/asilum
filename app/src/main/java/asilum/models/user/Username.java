package asilum.models.user;

import org.springframework.validation.annotation.Validated;

import javax.persistence.Embeddable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Embeddable
public class Username {

    @NotNull
    @Size(min=1, max=30, message="Invalid length.")
    private String username;

    public Username(){};


    public Username(String username){
        this.setUsername(username);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
