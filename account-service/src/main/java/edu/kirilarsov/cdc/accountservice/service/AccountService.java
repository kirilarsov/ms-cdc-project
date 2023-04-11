package edu.kirilarsov.cdc.accountservice.service;

import edu.kirilarsov.cdc.accountservice.exception.NonExistentAccountException;
import edu.kirilarsov.cdc.accountservice.model.Account;
import edu.kirilarsov.cdc.accountservice.repository.AccountRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * AccountService for retrieving accounts.
 */
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Optional<List<Account>> getAccountsByUser(String userUuid) {
        return accountRepository.findByUserUuid(userUuid);
    }
}
