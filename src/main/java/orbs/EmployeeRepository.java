package orbs;

import org.springframework.data.repository.PagingAndSortingRepository;

// This class is an interface-wrapper over JPA queries
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
	// There's no actual interface now.
}
