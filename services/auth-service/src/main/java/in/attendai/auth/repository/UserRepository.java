package in.attendai.auth.repository;

import in.attendai.auth.entity.User;
import in.attendai.auth.enums.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findByStatus(AccountStatus status);

    boolean existsByRollNumber(String rollNumber);

    boolean existsByEmployeeId(String employeeId);
}
