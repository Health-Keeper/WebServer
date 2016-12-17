package pl.pp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.pp.model.Account;

import java.util.Optional;

/**
 * Created by dpp on 12/17/16.
 */
@Transactional (propagation = Propagation.MANDATORY)
public interface AccountRepository extends PagingAndSortingRepository <Account, Long> {

    Optional<Account> findAccountByUsername (String username);
}
