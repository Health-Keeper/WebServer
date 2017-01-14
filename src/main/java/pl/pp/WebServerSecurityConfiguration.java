package pl.pp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

import javax.sql.DataSource;


/**
 * Created by dpp on 12/17/16.
 */

@Configuration
@EnableWebSecurity
@EnableAspectJAutoProxy
public class WebServerSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    public WebServerSecurityConfiguration () {

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder)
            throws Exception {

        /*builder.jdbcAuthentication()
                .dataSource(dataSource)
//                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery(
                        "SELECT username, password, is_active"
                                + " FROM Person"
                                + " WHERE username = ?"
                )
                .authoritiesByUsernameQuery(
                        "SELECT username USER_ROLE"
                                + " FROM Person"
                                + " WHERE username = ? AND is_active = True"
                );*/
        builder.inMemoryAuthentication().withUser("user1").password("password1").roles("USER");
        builder.inMemoryAuthentication().withUser("user2").password("password2").roles("USER");
        builder.inMemoryAuthentication().withUser("user3").password("password3").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/person/**").authenticated()
        .anyRequest().permitAll()
        .and().formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password")
        .and().logout().logoutSuccessUrl("/login")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }

    @Bean
    public SpringSecurityDialect springSecurityDialect(){
       return new SpringSecurityDialect();
    }
}
