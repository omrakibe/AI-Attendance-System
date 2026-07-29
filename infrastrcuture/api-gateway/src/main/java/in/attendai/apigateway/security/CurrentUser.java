package in.attendai.apigateway.security;

public record CurrentUser(
        Long userId,
        String email,
        String role
)
{
}
