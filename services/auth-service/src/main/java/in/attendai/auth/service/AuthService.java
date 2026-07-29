package in.attendai.auth.service;

import in.attendai.auth.entity.PasswordResetOtp;
import in.attendai.auth.entity.PendingRegistration;
import in.attendai.auth.entity.dto.*;
import in.attendai.auth.entity.User;
import in.attendai.auth.enums.AccountStatus;
import in.attendai.auth.enums.Role;
import in.attendai.auth.exception.*;
import in.attendai.auth.repository.PasswordResetOtpRepository;
import in.attendai.auth.repository.PendingRegistrationRepository;
import in.attendai.auth.repository.UserRepository;
import in.attendai.auth.security.CustomUserDetails;
import in.attendai.auth.security.JwtService;
import in.attendai.auth.util.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService implements IAuthService
{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PendingRegistrationRepository pendingRegistrationRepository;
    private final EmailService emailService;
    private final OtpUtil otpUtil;
    private final PasswordResetOtpRepository passwordResetOtpRepository;


    @Override
    @Transactional
    public ApiResponse register(RegisterRequest request)
    {

        String rollNumber = request.getRollNumber() == null
                ? null
                : request.getRollNumber().trim();

        String employeeId = request.getEmployeeId() == null
                ? null
                : request.getEmployeeId().trim();

        if (userRepository.existsByEmail(request.getEmail()))
        {
            throw new EmailAlreadyExistsException("Email already exists.");
        }

        if (request.getRole() == Role.ADMIN)
        {
            throw new InvalidRoleException("Admin registration is not allowed.");
        }

        if (request.getRole() == Role.STUDENT)
        {

            if (rollNumber == null || rollNumber.isBlank())
            {
                throw new InvalidRoleException("Roll number is required for students.");
            }

            if (userRepository.existsByRollNumber(rollNumber) ||
                    pendingRegistrationRepository.existsByRollNumber(rollNumber))
            {

                throw new RollNumberAlreadyExistsException("Roll number is already registered.");
            }

            if (employeeId != null &&
                    !employeeId.isBlank())
            {

                throw new InvalidRoleException("Students cannot have an employee ID.");
            }

        } else if (request.getRole() == Role.FACULTY)
        {

            if (employeeId == null || employeeId.isBlank())
            {
                throw new InvalidRoleException("Employee ID is required for faculty.");
            }

            if (userRepository.existsByEmployeeId(employeeId) ||
                    pendingRegistrationRepository.existsByEmployeeId(employeeId))
            {

                throw new EmployeeIdAlreadyExistsException("Employee ID is already registered.");
            }

            if (rollNumber != null &&
                    !rollNumber.isBlank())
            {

                throw new InvalidRoleException("Faculty cannot have a roll number.");
            }
        }

        Optional<PendingRegistration> existingRegistration =
                pendingRegistrationRepository.findByEmail(request.getEmail());

        if (existingRegistration.isPresent())
        {
            PendingRegistration pending = existingRegistration.get();

            if (pending.getOtpExpiry().isAfter(LocalDateTime.now()))
            {
                throw new PendingRegistrationExistsException(
                        "OTP has already been sent. Please verify your email."
                );
            }

            pendingRegistrationRepository.deleteByEmail(
                    request.getEmail()
            );
        }

        String otp = otpUtil.generateOtp();

        PendingRegistration pendingRegistration = PendingRegistration.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .encodedPassword(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .employeeId(employeeId)
                .rollNumber(rollNumber)
                .otp(otp)
                .otpExpiry(LocalDateTime.now().plusMinutes(5))
                .build();

        pendingRegistrationRepository.save(pendingRegistration);

        emailService.sendOtpEmail(
                pendingRegistration.getEmail(),
                otp
        );

//        User user = User.builder()
//                .fullName(request.getFullName())
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .role(request.getRole())
//                .status(AccountStatus.PENDING)
//                .build();
//
//        userRepository.save(user);

        return ApiResponse.builder()
                .success(true)
                .status(HttpStatus.CREATED.value())
                .message("OTP has been sent to your email. Please verify your email to complete registration.")
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    @Transactional
    public ApiResponse verifyOtp(VerifyOtpRequest request)
    {

        PendingRegistration pendingRegistration =
                pendingRegistrationRepository.findByEmail(request.getEmail())
                        .orElseThrow(() ->
                                new PendingRegistrationNotFoundException(
                                        "No pending registration found."
                                ));

        // Check if OTP has expired
        if (pendingRegistration.getOtpExpiry().isBefore(LocalDateTime.now()))
        {

            pendingRegistrationRepository.delete(pendingRegistration);

            throw new OtpExpiredException(
                    "OTP has expired. Please register again."
            );
        }

        // Verify OTP
        if (!pendingRegistration.getOtp().equals(request.getOtp()))
        {
            throw new InvalidOtpException("Invalid OTP.");
        }

        // Create User
        User user = User.builder()
                .fullName(pendingRegistration.getFullName())
                .email(pendingRegistration.getEmail())
                .password(pendingRegistration.getEncodedPassword()) // Already BCrypt encoded
                .role(pendingRegistration.getRole())
                .employeeId(pendingRegistration.getEmployeeId())
                .rollNumber(pendingRegistration.getRollNumber())
                .status(AccountStatus.PENDING)
                .build();

        userRepository.save(user);

        // Delete pending registration
        pendingRegistrationRepository.delete(pendingRegistration);

        return ApiResponse.builder()
                .success(true)
                .status(HttpStatus.CREATED.value())
                .message("Email verified successfully. Your registration is now pending administrator approval.")
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
                        .rollNumber(user.getRollNumber())
                        .employeeId(user.getEmployeeId())
                        .status(user.getStatus())
                        .createdAt(user.getCreatedAt())
                        .build())
                .toList();
    }

//    @Override
//    public InternalUserResponse getUsers(Long id)
//    {
//
//        User user = userRepository.findById(id)
//                .orElseThrow(() ->
//                        new UserNotFoundException("User not found with ID: " + id));
//
//        return InternalUserResponse.builder()
//                .id(user.getId())
//                .fullName(user.getFullName())
//                .email(user.getEmail())
//                .role(user.getRole())
//                .status(user.getStatus())
//                .rollNumber(user.getRollNumber())
//                .employeeId(user.getEmployeeId())
//                .build();
//    }

    @Override
    public InternalUserResponse getUserByEmployeeId(String employeeId)
    {

        User user = userRepository.findByEmployeeId(employeeId)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "Faculty not found: " + employeeId
                        ));

        return InternalUserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .employeeId(user.getEmployeeId())
                .rollNumber(user.getRollNumber())
                .role(user.getRole())
                .status(user.getStatus())
                .build();
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

        User admin = getCurrentUser();

        user.setStatus(AccountStatus.ACTIVE);
        user.setApprovedBy(admin);
        user.setApprovedAt(LocalDateTime.now());

        userRepository.save(user);
        emailService.sendApprovalEmail(user);

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

        User admin = getCurrentUser();

        user.setStatus(AccountStatus.REJECTED);
//        user.setApprovedBy(admin);
        user.setApprovedAt(LocalDateTime.now());

        userRepository.delete(user);
        emailService.sendRejectionEmail(user);
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

        String jwt = jwtService.generateToken(user);

        return LoginResponse.builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Login successful.")
                .token(jwt)
                .tokenType("Bearer")
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .accountStatus(user.getStatus())
                .rollNumber(user.getRollNumber())
                .employeeId(user.getEmployeeId())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    @Transactional
    public ApiResponse forgotPassword(ForgotPasswordRequest request)
    {

        // 1. Check if user exists
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new UserNotFoundException("No account found with this email."));

        // 2. Generate new OTP
        String otp = otpUtil.generateOtp();

        // 3. Check if a reset request already exists
        Optional<PasswordResetOtp> existingOtp =
                passwordResetOtpRepository.findByEmail(request.getEmail());

        if (existingOtp.isPresent())
        {

            PasswordResetOtp resetOtp = existingOtp.get();

            resetOtp.setOtp(otp);
            resetOtp.setOtpExpiry(LocalDateTime.now().plusMinutes(5));

            passwordResetOtpRepository.save(resetOtp);

        } else
        {

            PasswordResetOtp resetOtp = PasswordResetOtp.builder()
                    .email(user.getEmail())
                    .otp(otp)
                    .otpExpiry(LocalDateTime.now().plusMinutes(5))
                    .build();

            passwordResetOtpRepository.save(resetOtp);
        }

        // 4. Send OTP email
        emailService.sendPasswordResetOtp(user.getEmail(), otp);

        // 5. Response
        return ApiResponse.builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Password reset OTP has been sent to your registered email.")
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    @Transactional
    public ApiResponse resetPassword(ResetPasswordRequest request)
    {

        // 1. Find password reset record
        PasswordResetOtp resetOtp = passwordResetOtpRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new PasswordResetOtpNotFoundException(
                        "No password reset request found."
                ));

        // 2. Check OTP expiry
        if (resetOtp.getOtpExpiry().isBefore(LocalDateTime.now()))
        {
            throw new OtpExpiredException(
                    "OTP has expired. Please request a new password reset OTP."
            );
        }

        // 3. Verify OTP
        if (!resetOtp.getOtp().equals(request.getOtp()))
        {
            throw new InvalidOtpException("Invalid OTP.");
        }

        // 4. Find user
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException(
                        "User not found."
                ));

        // 5. Update password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        userRepository.save(user);

        // 6. Delete used OTP
        passwordResetOtpRepository.delete(resetOtp);

        // 7. Return response
        return ApiResponse.builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Password has been reset successfully.")
                .timestamp(LocalDateTime.now())
                .build();
    }

    private User getUserById(Long userId)
    {
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id: " + userId));
    }

    private User getCurrentUser()
    {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        return ((CustomUserDetails) authentication.getPrincipal()).getUser();
    }
}