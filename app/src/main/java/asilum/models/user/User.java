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

    @Nullable
    private String gender;

    @Nullable
    private Integer age;

    @Nullable
    private String country;

    @Nullable
    private String city;


    public User() {
    }

    public User(String username, String password, String photoUri, String gender, Integer age, String country, String city) {
        this.photoUri = photoUri;
        this.setUsername(username);
        this.setPassword(password);
        this.setGender(gender);
        this.setAge(age);
        this.setCountry(country);
        this.setCity(city);
    }

    public User(Username username, Password password, String photoUri, String gender, Integer age, String country, String city) {
        this.photoUri = photoUri;
        this.setUsername(username);
        this.setPassword(password);
        this.setGender(gender);
        this.setAge(age);
        this.setCountry(country);
        this.setCity(city);
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

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public void setUsername(asilum.models.user.Username username) {
        this.username = username;
    }

    public void setPassword(asilum.models.user.Password password) {
        this.password = password;
    }

    @Nullable
    public String getGender() {
        return gender;
    }

    public void setGender(@Nullable String gender) {
        this.gender = gender;
    }

    @Nullable
    public Integer getAge() {
        return age;
    }

    public void setAge(@Nullable Integer age) {
        this.age = age;
    }

    @Nullable
    public String getCountry() {
        return country;
    }

    public void setCountry(@Nullable String country) {
        this.country = country;
    }

    @Nullable
    public String getCity() {
        return city;
    }

    public void setCity(@Nullable String city) {
        this.city = city;
    }
}