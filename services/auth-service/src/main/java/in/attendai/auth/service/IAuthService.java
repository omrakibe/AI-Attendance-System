package in.attendai.auth.service;

import in.attendai.auth.entity.dto.*;

import java.util.List;

public interface IAuthService
{
    ApiResponse register(RegisterRequest request);

    ApiResponse verifyOtp(VerifyOtpRequest request);

    List<UserApprovalResponse> getPendingUsers();

    ApiResponse approveUser(Long userId);

    ApiResponse rejectUser(Long userId);

    LoginResponse login(LoginRequest request);
}
