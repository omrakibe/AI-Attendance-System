package in.attendai.auth.service;

import in.attendai.auth.entity.dto.ApiResponse;
import in.attendai.auth.entity.dto.RegisterRequest;
import in.attendai.auth.entity.dto.UserApprovalResponse;

import java.util.List;

public interface IAuthService
{
    ApiResponse register(RegisterRequest request);

    List<UserApprovalResponse> getPendingUsers();

    ApiResponse approveUser(Long userId);

    ApiResponse rejectUser(Long userId);
}
