package edu.kirilarsov.cdc.card.controller;

import edu.kirilarsov.cdc.card.client.CardClient;
import edu.kirilarsov.cdc.card.client.CardResponseDto;
import edu.kirilarsov.cdc.card.controller.dto.CardsWithTransactionDto;
import edu.kirilarsov.cdc.card.exception.RestApiErrorResponse;
import edu.kirilarsov.cdc.card.mapper.CardDtoMapper;
import edu.kirilarsov.cdc.card.service.CardService;
import edu.kirilarsov.cdc.card.transaction.TransactionRepository;
import edu.kirilarsov.cdc.card.transaction.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.Collections;
import javax.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * CardController for retrieving defining card related endpoints.
 */
@Validated
@RestController
@RequestMapping("/api/cards")
public class CardController implements CardClient {
    private final CardService cardService;
    private final TransactionService transactionService;
    private final CardDtoMapper cardDtoMapper;
    private static final Logger logger = LoggerFactory.getLogger(CardController.class);
    private static final String CARD_UUID_PREFIX = "card-uuid";

    /**
     * CardController.
     *
     * @param cardService CardService
     * @param cardDtoMapper CardDtoMapper
     * @param transactionService TransactionService
     *
     */
    public CardController(CardService cardService, CardDtoMapper cardDtoMapper,
                          TransactionService transactionService) {
        this.cardService = cardService;
        this.cardDtoMapper = cardDtoMapper;
        this.transactionService = transactionService;
    }

    /**
     * Get cards by accountUuid.
     *
     * @param accountUuid of the cards
     * @return card dto.
     */
    @Operation(summary = "Get cards by accountUuid")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cards dto",
            content = @Content(schema = @Schema(implementation = CardResponseDto.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = RestApiErrorResponse.class)))
    })
    @GetMapping
    public CardResponseDto getCardsByAccount(
        @RequestParam @NotBlank String accountUuid
    ) {

        logger.debug("Calling getCards for accountUuid: {}", accountUuid);
        var cards = cardService.getCardsByAccount(accountUuid).orElseGet(Collections::emptyList);

        var cardDtos = cards.stream()
            .map(cardDtoMapper::mapToCardDto)
            .toList();

        return new CardResponseDto(cardDtos);

    }

    /**
     * Get cards with transactions by accountUuid.
     *
     * @param accountUuid of the cards
     * @return card dto.
     */
    @Operation(summary = "Get cards with transactions by accountUuid")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cards dto",
            content = @Content(schema = @Schema(implementation = CardsWithTransactionDto.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = RestApiErrorResponse.class)))
    })
    @GetMapping(value = "/transactions")
    public CardsWithTransactionDto getCardsWithTransactionsByAccount(
        @RequestParam @NotBlank String accountUuid
    ) {

        logger.debug("Calling getCardsWithTransactionsByAccount for accountUuid: {}", accountUuid);
        var cards = cardService.getCardsByAccount(accountUuid).orElseGet(Collections::emptyList);

        var cardDtos = cards.stream()
            .map(card -> cardDtoMapper.mapToCardDtoWithTransactions(card,
                transactionService.getTransactionsByCard(CARD_UUID_PREFIX + card.getId())))
            .toList();

        return new CardsWithTransactionDto(cardDtos);

    }

}
