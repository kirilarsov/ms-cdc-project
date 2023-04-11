package edu.kirilarsov.cdc.card.mapper;

import edu.kirilarsov.cdc.card.client.CardResponseDto;
import edu.kirilarsov.cdc.card.controller.dto.CardsWithTransactionDto;
import edu.kirilarsov.cdc.card.model.Card;
import edu.kirilarsov.cdc.transaction.client.TransactionResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * CardDtoMapper for mapping card related objects.
 */
@Mapper(componentModel = "spring")
public interface CardDtoMapper {

    @Mapping(source = "status", target = "status", qualifiedByName = "mapCardStatus")
    CardResponseDto.CardDto mapToCardDto(Card card);

    @Named("mapCardStatus")
    static String mapStatus(Card.Status status) {
        return status.name();
    }

    @Mapping(source = "card.status", target = "status", qualifiedByName = "mapCardStatus")
    CardsWithTransactionDto.CardDto mapToCardDtoWithTransactions(
        Card card,
        TransactionResponseDto transactionResponseDto);
}
