package edu.kirilarsov.cdc.accountservice.card;

import edu.kirilarsov.cdc.card.client.CardResponseDto;
import org.springframework.stereotype.Service;

/**
 * CardService class for providing behaviour on card related data.
 */
@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public CardResponseDto getCardsByAccount(String accountUuid) {
        return cardRepository.getCardsByAccount(accountUuid);
    }

}
