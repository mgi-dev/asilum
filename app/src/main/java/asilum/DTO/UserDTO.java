package asilum.DTO;

import asilum.models.user.User;

public class UserDTO {
    private String username;
    private String photoUri;
    private int id;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername().getUsername();
        this.photoUri = user.getPhotoUri();
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
}
