package asilum.models.users;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;


@Embeddable
public class Username {

    @Size(min=1, max=30, message="Invalid length.")
    private String username;

    public Username(){}

    public Username(String username) {
        this.setUsername(username);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
