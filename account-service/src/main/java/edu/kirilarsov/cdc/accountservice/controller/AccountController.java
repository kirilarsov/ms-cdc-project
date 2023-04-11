package edu.kirilarsov.cdc.accountservice.controller;

import edu.kirilarsov.cdc.accountservice.card.CardService;
import edu.kirilarsov.cdc.accountservice.controller.dto.AccountResponseDto;
import edu.kirilarsov.cdc.accountservice.controller.dto.AccountWithCardsResponseDto;
import edu.kirilarsov.cdc.accountservice.controller.dto.AccountWithTransactionsResponseDto;
import edu.kirilarsov.cdc.accountservice.exception.RestApiErrorResponse;
import edu.kirilarsov.cdc.accountservice.mapper.AccountDtoMapper;
import edu.kirilarsov.cdc.accountservice.service.AccountService;
import edu.kirilarsov.cdc.accountservice.transaction.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.Collections;
import javax.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * AccountController for retrieving defining account related endpoints.
 */
@Validated
@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private static final String ACCOUNT_UUID_PREFIX = "account-uuid";
    private final AccountService accountService;
    private final CardService cardService;
    private final TransactionService transactionService;
    private final AccountDtoMapper accountDtoMapper;

    /**
     * Account controller constructor.
     *
     * @param accountService   AccountService
     * @param accountDtoMapper AccountDtoMapper
     * @param cardService      CardService
     * @param transactionService      TransactionService
     */
    public AccountController(AccountService accountService, AccountDtoMapper accountDtoMapper,
                             CardService cardService, TransactionService transactionService) {
        this.accountService = accountService;
        this.cardService = cardService;
        this.transactionService = transactionService;
        this.accountDtoMapper = accountDtoMapper;
    }

    /**
     * Get accounts by userUuid.
     *
     * @param userUuid of the accounts
     * @return account dto.
     */
    @Operation(summary = "Get accounts by userUuid")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Accounts dto",
            content = @Content(schema = @Schema(implementation = AccountResponseDto.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = RestApiErrorResponse.class)))
    })
    @GetMapping
    public ResponseEntity<AccountResponseDto> getAccountsByUser(
        @RequestParam @NotBlank String userUuid
    ) {


        var accounts = accountService.getAccountsByUser(userUuid).orElseGet(Collections::emptyList);

        var accountDtos = accounts.stream()
            .map(accountDtoMapper::mapToAccountDto)
            .toList();

        return ResponseEntity.ok(new AccountResponseDto(accountDtos));

    }

    /**
     * Get accounts with cards by userUuid.
     *
     * @param userUuid of the accounts
     * @return account with cards dto.
     */
    @Operation(summary = "Get accounts with cards by userUuid")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Accounts with cards dto",
            content = @Content(schema = @Schema(implementation = AccountWithCardsResponseDto.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = RestApiErrorResponse.class)))
    })
    @GetMapping(value = "/cards")
    public ResponseEntity<AccountWithCardsResponseDto> getAccountsWithCardsByUser(
        @RequestParam @NotBlank String userUuid
    ) {


        var accounts = accountService.getAccountsByUser(userUuid).orElseGet(Collections::emptyList);

        var accountDtos = accounts.stream()
            .map(account -> accountDtoMapper.mapToAccountWithCardsDto(account,
                cardService.getCardsByAccount(ACCOUNT_UUID_PREFIX + account.getId())))
            .toList();

        return ResponseEntity.ok(new AccountWithCardsResponseDto(accountDtos));

    }

    /**
     * Get accounts with transactions by userUuid.
     *
     * @param userUuid of the accounts
     * @return account with cards dto.
     */
    @Operation(summary = "Get accounts with transactions by userUuid")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Accounts with transactions dto",
            content = @Content(schema = @Schema(implementation = AccountWithTransactionsResponseDto.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = RestApiErrorResponse.class)))
    })
    @GetMapping(value = "/transactions")
    public ResponseEntity<AccountWithTransactionsResponseDto> getAccountsWithTransactionsByUser(
        @RequestParam @NotBlank String userUuid
    ) {


        var accounts = accountService.getAccountsByUser(userUuid).orElseGet(Collections::emptyList);

        var accountDtos = accounts.stream()
            .map(account -> accountDtoMapper.mapToAccountWithTransactionsDto(account,
                transactionService.getTransactionsByAccount(ACCOUNT_UUID_PREFIX + account.getId())))
            .toList();

        return ResponseEntity.ok(new AccountWithTransactionsResponseDto(accountDtos));

    }

}
