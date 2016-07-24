package orbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// This class can bootstrap spring environment without manual deployment.
// Just run in terminal: java -jar target/orbs.jar
@SpringBootApplication
public class OrbsApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrbsApplication.class, args);
    }
}
