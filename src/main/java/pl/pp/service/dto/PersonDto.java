//package pl.pp.service.dto;
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//import pl.pp.model.Account;
//
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//
//import static javax.swing.text.StyleConstants.Size;
//
///**
// * Created by dpp on 12/17/16.
// */
//public class AccountDto {
//
//    @NotNull
//    @Size(min = 1)
//    private String username;
//
//    @NotNull
//    @Size(min = 4)
//    private String password;
//
//    public void applyToEntity(Account account,
//                              PasswordEncoder passwordEncoder) {
//        account.setUsername(username);
//        account.setPassword(passwordEncoder.encode(password));
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//}
