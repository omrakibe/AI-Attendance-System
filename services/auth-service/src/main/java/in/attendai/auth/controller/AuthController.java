package in.attendai.auth.controller;

import in.attendai.auth.entity.dto.*;
import in.attendai.auth.service.AuthService;
import in.attendai.auth.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController
{

    private final AuthService authService;
    private final EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(
            @Valid @RequestBody RegisterRequest request)
    {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.register(request));
    }

    @GetMapping("/pending-users")
    public ResponseEntity<List<UserApprovalResponse>> getPendingUsers()
    {
        return ResponseEntity.ok(authService.getPendingUsers());
    }

    @PutMapping("/approve/{userId}")
    public ResponseEntity<ApiResponse> approveUser(@PathVariable Long userId)
    {
        return ResponseEntity.ok(authService.approveUser(userId));
    }

    @PutMapping("/reject/{userId}")
    public ResponseEntity<ApiResponse> rejectUser(@PathVariable Long userId)
    {
        return ResponseEntity.ok(authService.rejectUser(userId));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request)
    {

        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<ApiResponse> verifyOtp(
            @Valid @RequestBody VerifyOtpRequest request)
    {
        return ResponseEntity.ok(authService.verifyOtp(request));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse> forgotPassword(
            @Valid @RequestBody ForgotPasswordRequest request)
    {

        return ResponseEntity.ok(authService.forgotPassword(request));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse> resetPassword(
            @Valid @RequestBody ResetPasswordRequest request)
    {

        return ResponseEntity.ok(authService.resetPassword(request));
    }

//    @GetMapping("/test-email")
//    public String testEmail()
//    {
//        emailService.sendSimpleEmail(
//                "rakibeom30@gmail.com",
//                "AttendAI Test Email",
//                "Congratulations! Your email configuration is working."
//        );
//
//        return "Email sent successfully.";
//    }
}
