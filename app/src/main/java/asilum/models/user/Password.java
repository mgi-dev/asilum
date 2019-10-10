package asilum.models.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;


@Embeddable
@Validated
public class Password {
    @Size(min=8, max=60, message="Invalid length.")
    private String password;

    public Password(){}

    public Password(String password){
        this.setPassword(password);
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void selfHash(){
        this.password = passwordEncoder().encode(this.password);
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public Boolean match(String password){
        return passwordEncoder().matches(password, this.password);
    }
}
