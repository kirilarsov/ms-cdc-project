package edu.kirilarsov.cdc.transaction.controller;

import edu.kirilarsov.cdc.transaction.client.TransactionClient;
import edu.kirilarsov.cdc.transaction.client.TransactionResponseDto;
import edu.kirilarsov.cdc.transaction.exception.RestApiErrorResponse;
import edu.kirilarsov.cdc.transaction.mapper.TransactionDtoMapper;
import edu.kirilarsov.cdc.transaction.service.TransactionService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TransactionController for retrieving defining transaction related endpoints.
 */
@Validated
@RestController
@RequestMapping("/api/transactions")
public class TransactionController implements TransactionClient {
    private final TransactionService transactionService;
    private final TransactionDtoMapper transactionDtoMapper;
    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    public TransactionController(TransactionService transactionService, TransactionDtoMapper transactionDtoMapper) {
        this.transactionService = transactionService;
        this.transactionDtoMapper = transactionDtoMapper;
    }

    /**
     * Get transactions by accountUuid.
     *
     * @param accountUuid of the transactions
     * @return transaction dto.
     */
    @Operation(summary = "Get transactions by accountUuid")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Transactions dto",
            content = @Content(schema = @Schema(implementation = TransactionResponseDto.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = RestApiErrorResponse.class)))
    })
    @GetMapping(value = "/account/{accountUuid}")
    public TransactionResponseDto getTransactionsByAccount(
        @PathVariable String accountUuid
    ) {

        logger.debug("Calling getTransactionsByAccount for accountUuid: {}", accountUuid);
        var transactions = transactionService.getTransactionsByAccount(accountUuid).orElseGet(Collections::emptyList);

        var transactionDtos = transactions.stream()
            .map(transactionDtoMapper::mapToTransactionDto)
            .toList();

        return new TransactionResponseDto(transactionDtos);

    }

    /**
     * Get transactions by cardUuid.
     *
     * @param cardUuid of the transactions
     * @return transaction dto.
     */
    @Operation(summary = "Get transactions by cardUuid")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Transactions dto",
            content = @Content(schema = @Schema(implementation = TransactionResponseDto.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = RestApiErrorResponse.class)))
    })
    @GetMapping(value = "/card/{cardUuid}")
    public TransactionResponseDto getTransactionsByCard(
        @PathVariable String cardUuid
    ) {

        logger.debug("Calling getTransactionsByCard for accountUuid: {}", cardUuid);
        var transactions = transactionService.getTransactionsByCard(cardUuid).orElseGet(Collections::emptyList);

        var transactionDtos = transactions.stream()
            .map(transactionDtoMapper::mapToTransactionDto)
            .toList();

        return new TransactionResponseDto(transactionDtos);

    }
}
