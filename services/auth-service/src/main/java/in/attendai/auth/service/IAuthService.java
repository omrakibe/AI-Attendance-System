package in.attendai.auth.service;

import in.attendai.auth.dto.ApiResponse;
import in.attendai.auth.dto.RegisterRequest;

public interface IAuthService
{
    ApiResponse register(RegisterRequest request);
}
