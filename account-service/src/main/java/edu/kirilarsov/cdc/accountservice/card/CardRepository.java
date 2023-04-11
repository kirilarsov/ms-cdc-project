package edu.kirilarsov.cdc.accountservice.card;

import edu.kirilarsov.cdc.card.client.CardResponseDto;

/**
 * CardRepository interface for defining access behaviour for the card-service.
 */
public interface CardRepository {

    CardResponseDto getCardsByAccount(String accountUuid);

}
