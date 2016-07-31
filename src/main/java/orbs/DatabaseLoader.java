package orbs;

import orbs.models.entities.User;
import orbs.models.entities.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {
	private final UserRepository repository;

	@Autowired
	public DatabaseLoader(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(String... strings) throws Exception {
		this.repository.save(new User("Frodo"));
		this.repository.save(new User("Bilbo"));
		this.repository.save(new User("Gandalf"));
		this.repository.save(new User("Samwise"));
		this.repository.save(new User("Meriadoc"));
		this.repository.save(new User("Peregrin"));
	}
}
