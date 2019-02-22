package ru.mityushin.responder.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mityushin.responder.entity.MessageNewCallback;

/**
 * Repository for database entity {@code MessageNewCallback}
 *
 * @author Dmitry Mityushin
 * @see ru.mityushin.responder.entity.MessageNewCallback
 * @see org.springframework.data.repository.CrudRepository
 * @since 1.0
 */
@Repository
public interface MessageNewCallbackRepository extends CrudRepository<MessageNewCallback, Long> {
}
