package pl.pp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pp.model.Person;
import pl.pp.repository.PersonRepository;
import pl.pp.service.dto.PersonDto;

import java.util.Optional;

/**
 * Created by dpp on 12/17/16.
 */
@Transactional
@Service
public class PersonService {

    private final Logger log = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PersonRepository accountRepository;

    public void createAccount (PersonDto accountDto) {
        checkUniquenessOfUsername(accountDto.getUsername());
        checkPassword(accountDto.getPassword());

        Person newAccount = new Person();
        accountDto.applyToEntity(newAccount, passwordEncoder);
        newAccount.setIs_active(true);
        accountRepository.save(newAccount);
        log.info("New account created {0}", newAccount.getUsername());
    }

    @Transactional(readOnly = true)
    public Person findPerson(long id) {
        return accountRepository.findOne(id);
    }

    private Optional<Person> findLoggedUser() {
        Authentication authentication
                = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return Optional.empty();
        }
        String username = authentication.getName();
        return accountRepository.findAccountByUsername(username);
    }

    private void checkPassword (String password) {
        if (password == null || password.length() < 4) {
            throw new IllegalArgumentException ("Password should have at last 4 characters");
        }
    }

    private void checkUniquenessOfUsername (String username) {
        if (accountRepository.findAccountByUsername(username).isPresent()) {
            throw new IllegalArgumentException(username + ": account already exists");
        }
    }

}
