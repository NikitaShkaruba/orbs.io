package orbs;

import orbs.models.SessionProcessor;
import orbs.models.SessionProcessorImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

// This class can bootstrap spring environment without manual deployment.
// Just run in terminal: java -jar target/orbs.jar
@SpringBootApplication 	// Alternative to @Configuration, @EnableAutoConfiguration and @ComponentScan
public class OrbsApplication {
	public static void main(String[] args) {
		SpringApplication.run(OrbsApplication.class, args);
	}

	@Bean
	public SessionProcessor sessionProcessor() {
		return new SessionProcessorImpl();
	}
}
