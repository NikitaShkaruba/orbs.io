package orbs;

import org.springframework.data.repository.CrudRepository;

// This class is an interface-wrapper over JPA queries
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	// There's no actual interface now.
}
