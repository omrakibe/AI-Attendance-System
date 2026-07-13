package in.attendai.auth.service;

import in.attendai.auth.entity.dto.*;
import in.attendai.auth.entity.User;
import in.attendai.auth.enums.AccountStatus;
import in.attendai.auth.enums.Role;
import in.attendai.auth.exception.*;
import in.attendai.auth.repository.UserRepository;
import in.attendai.auth.security.CustomUserDetails;
import in.attendai.auth.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService implements IAuthService
{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

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

    @Override
    @Transactional(readOnly = true)
    public List<UserApprovalResponse> getPendingUsers()
    {
        return userRepository.findByStatus(AccountStatus.PENDING)
                .stream()
                .map(user -> UserApprovalResponse.builder()
                        .id(user.getId())
                        .fullName(user.getFullName())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .status(user.getStatus())
                        .createdAt(user.getCreatedAt())
                        .build())
                .toList();
    }

    @Override
    @Transactional
    public ApiResponse approveUser(Long userId)
    {

        User user = getUserById(userId);

        if (user.getStatus() == AccountStatus.ACTIVE)
        {
            throw new AccountAlreadyProcessedException("User account is already approved.");
        }

        if (user.getStatus() == AccountStatus.REJECTED)
        {
            throw new AccountAlreadyProcessedException("Rejected account cannot be approved.");
        }

        user.setStatus(AccountStatus.ACTIVE);
        user.setApprovedAt(LocalDateTime.now());

        userRepository.save(user);

        return ApiResponse.builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Account approved successfully.")
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    @Transactional
    public ApiResponse rejectUser(Long userId)
    {

        User user = getUserById(userId);

        if (user.getStatus() == AccountStatus.REJECTED)
        {
            throw new AccountAlreadyProcessedException("User account is already rejected.");
        }

        if (user.getStatus() == AccountStatus.ACTIVE)
        {
            throw new AccountAlreadyProcessedException("Approved account cannot be rejected.");
        }

        user.setStatus(AccountStatus.REJECTED);
        user.setApprovedAt(LocalDateTime.now());

        userRepository.save(user);

        return ApiResponse.builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Account rejected successfully.")
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest request)
    {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        if (user.getStatus() == AccountStatus.PENDING)
        {
            throw new AccountPendingException(
                    "Your account is pending approval.");
        }

        if (user.getStatus() == AccountStatus.REJECTED)
        {
            throw new AccountRejectedException(
                    "Your account has been rejected. Please contact the administrator.");
        }

        String jwt = jwtService.generateToken(new CustomUserDetails(user));

        return LoginResponse.builder()
                .token(jwt)
                .type("Bearer")
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    private User getUserById(Long userId)
    {
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id: " + userId));
    }
}