package edu.kirilarsov.cdc.accountservice.repository;

import edu.kirilarsov.cdc.accountservice.model.Account;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

/**
 * Account repository.
 *
 */
public interface AccountRepository {

    Optional<List<Account>> findByUserUuid(String userUuid);

    @Query(value = "SELECT 1", nativeQuery = true)
    void health();
}
