package ru.mityushin.responder.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mityushin.responder.entity.MessageNewCallback;

@Repository
public interface MessageNewCallbackRepository extends CrudRepository<MessageNewCallback, Long> {
}
