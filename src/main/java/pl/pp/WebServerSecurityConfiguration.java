package pl.pp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.activation.DataSource;

/**
 * Created by dpp on 12/17/16.
 */

@Configuration
@EnableWebSecurity
public class WebServerSecurityConfiguration extends WebSecurityConfigurerAdapter {

    public WebServerSecurityConfiguration () {

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
