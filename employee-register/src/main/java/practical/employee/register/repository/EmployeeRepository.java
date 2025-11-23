package practical.employee.register.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practical.employee.register.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>
{
}
