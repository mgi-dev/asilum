package asilum.services;

import asilum.DTO.UserDTO;
import asilum.exceptions.UserNotFoundException;
import asilum.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO getUserById(Integer id) throws UserNotFoundException {

        return new UserDTO(userRepository.findById(id).orElseThrow(UserNotFoundException::new));
    }
}
