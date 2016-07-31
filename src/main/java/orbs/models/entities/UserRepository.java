package orbs.models.entities;

import org.springframework.data.repository.PagingAndSortingRepository;

// This class is an interface-wrapper over JPA queries
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	// There's no actual interface now.
}
