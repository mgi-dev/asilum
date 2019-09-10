package asilum.repositories;

import asilum.models.User;
import asilum.models.users.Username;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsernameAndPassword(Username name, String password);

}