package asilum.DTO;

import asilum.models.user.User;

public class UserDTO {
    private String username;
//    private String password;

    public UserDTO(User user) {
        this.username = user.getUsername().getUsername();
//        this.password = user.getPassword().getPassword();
    }

    public String getUsername() {
        return username;
    }

//    public String getPassword() {
//        return password;
//    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public void setPassword(String password) {
//        this.password = password;
//    }
}
