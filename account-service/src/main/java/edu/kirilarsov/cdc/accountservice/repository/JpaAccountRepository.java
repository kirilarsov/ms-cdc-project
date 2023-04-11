package edu.kirilarsov.cdc.accountservice.repository;


import edu.kirilarsov.cdc.accountservice.model.Account;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Jpa Account repository.
 *
 */
@Primary
@Repository
public interface JpaAccountRepository extends JpaRepository<Account, Long>, AccountRepository {

}
