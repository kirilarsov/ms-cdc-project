package edu.kirilarsov.cdc.card.repository;


import edu.kirilarsov.cdc.card.model.Card;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Jpa Card repository.
 *
 */
@Primary
@Repository
public interface JpaCardRepository extends JpaRepository<Card, Long>, CardRepository {

}
