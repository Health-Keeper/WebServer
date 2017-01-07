//package pl.pp.service;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import pl.pp.model.Account;
//import pl.pp.repository.AccountRepository;
//import pl.pp.service.dto.AccountDto;
//
///**
// * Created by dpp on 12/17/16.
// */
//@Transactional
//@Service
//public class AccountService {
//
//    private final Logger log = LoggerFactory.getLogger(AccountService.class);
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private AccountRepository accountRepository;
//
//    public void createAccount (AccountDto accountDto) {
//        checkUniquenessOfUsername(accountDto.getUsername());
//        checkPassword(accountDto.getPassword());
//
//        Account newAccount = new Account();
//        accountDto.applyToEntity(newAccount, passwordEncoder);
//        newAccount.setIs_active(true);
//        accountRepository.save(newAccount);
//        log.info("New account created {0}", newAccount.getUsername());
//    }
//
//    private void checkPassword (String password) {
//        if (password == null || password.length() < 4) {
//            throw new IllegalArgumentException ("Password should have at last 4 characters");
//        }
//    }
//
//    private void checkUniquenessOfUsername (String username) {
//        if (accountRepository.findAccountByUsername(username).isPresent()) {
//            throw new IllegalArgumentException(username + ": account already exists");
//        }
//    }
//
//}
