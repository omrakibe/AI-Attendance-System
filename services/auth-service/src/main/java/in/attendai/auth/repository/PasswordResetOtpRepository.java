package in.attendai.auth.repository;

import in.attendai.auth.entity.PasswordResetOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetOtpRepository
        extends JpaRepository<PasswordResetOtp, Long>
{

    Optional<PasswordResetOtp> findByEmail(String email);

    boolean existsByEmail(String email);

    void deleteByEmail(String email);
}
