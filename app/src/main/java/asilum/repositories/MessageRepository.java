package asilum.repositories;

import asilum.models.message.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Integer> {
    List<Message> findBySenderId(Integer id);
}
