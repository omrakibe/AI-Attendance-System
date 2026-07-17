package in.attendai.auth.exception;

import in.attendai.auth.entity.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationException(
            MethodArgumentNotValidException ex)
    {

        String message = Objects.requireNonNull(ex.getBindingResult()
                        .getFieldError())
                .getDefaultMessage();

        return buildErrorResponse(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse> handleUserNotFound(
            UserNotFoundException ex)
    {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse> handleBadCredentialsException(
            BadCredentialsException ex)
    {
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Invalid email or password.");
    }

    @ExceptionHandler(AccountPendingException.class)
    public ResponseEntity<ApiResponse> handleAccountPendingException(
            AccountPendingException ex)
    {

        return buildErrorResponse(HttpStatus.FORBIDDEN, ex.getMessage());
    }

    @ExceptionHandler(AccountRejectedException.class)
    public ResponseEntity<ApiResponse> handleAccountRejectedException(
            AccountRejectedException ex)
    {

        return buildErrorResponse(HttpStatus.FORBIDDEN, ex.getMessage());
    }

    @ExceptionHandler(PendingRegistrationNotFoundException.class)
    public ResponseEntity<ApiResponse> handlePendingRegistrationNotFoundException(
            PendingRegistrationNotFoundException ex)
    {

        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(PasswordResetOtpNotFoundException.class)
    public ResponseEntity<ApiResponse> handlePasswordResetOtpNotFoundException(
            PasswordResetOtpNotFoundException ex)
    {

        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler({
            EmailAlreadyExistsException.class,
            RollNumberAlreadyExistsException.class,
            EmployeeIdAlreadyExistsException.class,
            InvalidRegistrationException.class,
            InvalidOtpException.class,
            OtpExpiredException.class,
            PendingRegistrationExistsException.class,
            AccountAlreadyProcessedException.class,
            InvalidRoleException.class
    })
    public ResponseEntity<ApiResponse> handleBadRequestExceptions(RuntimeException ex)
    {

        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGenericException(
            Exception ex)
    {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred.");
    }

    private ResponseEntity<ApiResponse> buildErrorResponse(
            HttpStatus status,
            String message)
    {

        ApiResponse response = ApiResponse.builder()
                .success(false)
                .status(status.value())
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(status).body(response);
    }
}
