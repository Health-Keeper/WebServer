package pl.pp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import pl.pp.model.Person;
import pl.pp.repository.PersonRepository;

@SpringBootApplication
public class WebServerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(WebServerApplication.class, args);
	}

	@Bean
    public RestTemplate restTemplate (RestTemplateBuilder builder) {
	    return builder.build();
    }

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WebServerApplication.class);
	}

//	@Bean
//	public CommandLineRunner demo(PersonRepository personRepository) {
//
//		PasswordEncoder pe = new BCryptPasswordEncoder();
//		Person person = new Person();
//
//		person.setUsername("Gustavus");
//		person.setPassword(pe.encode("12345"));
//		person.setIs_active(false);
//
//		return (args) -> {
//			personRepository.deleteAll();
//			personRepository.save(person);
//		};
//	}
}
