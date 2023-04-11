package edu.kirilarsov.cdc.accountservice.card.logger;

import edu.kirilarsov.cdc.accountservice.card.CardRepository;
import edu.kirilarsov.cdc.card.client.CardResponseDto;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("card-logger-client")
class CardRepositoryLogger implements CardRepository {

    private static final Logger logger = LoggerFactory.getLogger(CardRepositoryLogger.class);


    @Override
    public CardResponseDto getCardsByAccount(String accountUuid) {
        logger.info("Method invoked: getCards() from CardRepositoryLogger");
        return new CardResponseDto(
            List.of(new CardResponseDto.CardDto(
                "account-uuid-1",
                "Tom Jonson",
                "1234-5678-9012-3456",
                "11/29",
                "123",
                CardResponseDto.CardDto.Status.ACTIVE
            )));
    }


}
