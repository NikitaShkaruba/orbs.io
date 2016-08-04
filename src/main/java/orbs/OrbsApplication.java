package orbs;

import orbs.models.SessionProcessor;
import orbs.models.World;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

// This class can bootstrap spring environment without manual deployment.
// Just run in terminal: java -jar target/orbs.jar
@SpringBootApplication 					// Alternative to @Configuration, @EnableAutoConfiguration and @ComponentScan
public class OrbsApplication {
	public static void main(String[] args) {
		SpringApplication.run(OrbsApplication.class, args);
	}

	// Below are defined all the beans managed by the container.
	@Bean
	@Scope("singleton")
	public World world() {
		return new World();
	}
	@Bean
	@Scope("prototype") // Todo: change scope from "prototype" to "session"
	public SessionProcessor sessionProcessor() {
		return new SessionProcessor();
	}
}
