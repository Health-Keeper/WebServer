package pl.pp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.pp.model.Person;

import java.util.Optional;

/**
 * Created by dpp on 12/17/16.
 */
@Transactional (propagation = Propagation.MANDATORY)
public interface PersonRepository extends PagingAndSortingRepository <Person, Long> {

    Optional<Person> findAccountByUsername (String username);
}
