package pl.pp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * Created by dpp on 12/17/16.
 */

@Configuration
//@EnableWebSecurity
//@EnableAspectJAutoProxy
public class WebServerSecurityConfiguration {

    public WebServerSecurityConfiguration () {

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder builder)
//            throws Exception {
//
//        builder.jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(passwordEncoder())
//                .usersByUsernameQuery(
//                        "SELECT username, password, active"
//                                + " FROM account"
//                                + " WHERE username = ?"
//                )
//                .authoritiesByUsernameQuery(
//                        "SELECT account.username, role.type"
//                                + " FROM role"
//                                + " INNER JOIN account"
//                                + " ON role.account_id = account.account_id"
//                                + " WHERE account.username = ? AND role.active = TRUE"
//                );
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest().permitAll();
//        http.formLogin().loginPage("/login");
//        http.logout().logoutSuccessUrl("/")
//                .logoutRequestMatcher(new AntPathRequestMatcher("/"));
//    }
}
