package edu.kirilarsov.cdc.accountservice.card.web;

import edu.kirilarsov.cdc.accountservice.card.CardRepository;
import edu.kirilarsov.cdc.card.client.CardClient;
import edu.kirilarsov.cdc.card.client.CardResponseDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Card Repository Web Impl.
 */
@Component
@Profile("card-web-client")
public class CardRepositoryWeb implements CardRepository {

    private final CardClient cardClient;

    public CardRepositoryWeb(CardClient cardClient) {
        this.cardClient = cardClient;
    }

    @Override
    public CardResponseDto getCardsByAccount(String accountUuid) {
        return cardClient.getCardsByAccount(accountUuid);
    }

}
