package pl.pp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest().permitAll();
//        http.formLogin().loginPage("/");
//        http.logout().logoutSuccessUrl("/")
//                .logoutRequestMatcher(new AntPathRequestMatcher("/"));
//    }
}
