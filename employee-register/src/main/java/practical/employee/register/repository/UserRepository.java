package practical.employee.register.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practical.employee.register.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
    User findAllByUsername(String username);
}
