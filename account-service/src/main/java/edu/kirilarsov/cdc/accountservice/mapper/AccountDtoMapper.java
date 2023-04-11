package edu.kirilarsov.cdc.accountservice.mapper;

import edu.kirilarsov.cdc.accountservice.controller.dto.AccountResponseDto;
import edu.kirilarsov.cdc.accountservice.controller.dto.AccountWithCardsResponseDto;
import edu.kirilarsov.cdc.accountservice.controller.dto.AccountWithTransactionsResponseDto;
import edu.kirilarsov.cdc.accountservice.model.Account;
import edu.kirilarsov.cdc.card.client.CardResponseDto;
import edu.kirilarsov.cdc.transaction.client.TransactionResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * AccountDtoMapper for mapping account related objects.
 */
@Mapper(componentModel = "spring")
public interface AccountDtoMapper {

    @Mapping(source = "currency", target = "currency", qualifiedByName = "mapAccountCurrency")
    AccountResponseDto.AccountDto mapToAccountDto(Account account);

    @Named("mapAccountCurrency")
    static String mapCurrency(Account.Currency currency) {
        return currency.name();
    }

    @Mapping(source = "account.currency", target = "currency", qualifiedByName = "mapAccountCurrency")
    AccountWithCardsResponseDto.AccountDto mapToAccountWithCardsDto(Account account, CardResponseDto cardResponseDto);

    @Mapping(source = "account.currency", target = "currency", qualifiedByName = "mapAccountCurrency")
    AccountWithTransactionsResponseDto.AccountDto mapToAccountWithTransactionsDto(
        Account account,
        TransactionResponseDto transactionResponseDto);
}
