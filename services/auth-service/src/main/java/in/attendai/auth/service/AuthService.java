package in.attendai.auth.service;

import in.attendai.auth.dto.ApiResponse;
import in.attendai.auth.dto.RegisterRequest;
import in.attendai.auth.entity.User;
import in.attendai.auth.enums.AccountStatus;
import in.attendai.auth.enums.Role;
import in.attendai.auth.exception.EmailAlreadyExistsException;
import in.attendai.auth.exception.InvalidRoleException;
import in.attendai.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService implements IAuthService
{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ApiResponse register(RegisterRequest request)
    {

        if (userRepository.existsByEmail(request.getEmail()))
        {
            throw new EmailAlreadyExistsException("Email already exists.");
        }

        if (request.getRole() == Role.ADMIN)
        {
            throw new InvalidRoleException("Admin registration is not allowed.");
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .status(AccountStatus.PENDING)
                .build();

        userRepository.save(user);

        return ApiResponse.builder()
                .success(true)
                .status(HttpStatus.CREATED.value())
                .message("Registration request submitted successfully. Waiting for approval.")
                .timestamp(LocalDateTime.now())
                .build();
    }
}