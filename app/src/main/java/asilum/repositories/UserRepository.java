package asilum.repositories;

import asilum.models.user.User;
import asilum.models.user.Username;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUsernameAndPassword(String name, String password);

    Optional<User> findByUsername(Username username);
}