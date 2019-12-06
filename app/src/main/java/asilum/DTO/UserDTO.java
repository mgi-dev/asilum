package asilum.DTO;

import asilum.models.user.User;

public class UserDTO {
    private String username;
    private String photoUri;
    private int id;
    private String gender;
    private Integer age;
    private String country;
    private String city;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername().getUsername();
        this.photoUri = user.getPhotoUri();
        this.gender = user.getGender();
        this.age = user.getAge();
        this.country = user.getCountry();
        this.city = user.getCity();

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public String getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }
}
