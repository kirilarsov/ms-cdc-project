package edu.kirilarsov.cdc.card.repository;

import edu.kirilarsov.cdc.card.model.Card;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

/**
 * Card repository.
 *
 */
public interface CardRepository {

    Optional<List<Card>> findByAccountUuid(String accountUuid);

    @Query(value = "SELECT 1", nativeQuery = true)
    void health();
}
