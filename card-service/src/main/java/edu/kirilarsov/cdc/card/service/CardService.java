package edu.kirilarsov.cdc.card.service;

import edu.kirilarsov.cdc.card.model.Card;
import edu.kirilarsov.cdc.card.repository.CardRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * CardService for retrieving cards.
 */
@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Optional<List<Card>> getCardsByAccount(String accountUuid) {
        return cardRepository.findByAccountUuid(accountUuid);
    }
}
