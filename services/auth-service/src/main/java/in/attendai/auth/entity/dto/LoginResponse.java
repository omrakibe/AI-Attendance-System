package in.attendai.auth.entity.dto;

import in.attendai.auth.enums.AccountStatus;
import in.attendai.auth.enums.Role;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse
{

    private boolean success;
    private int status;
    private String message;

    private String token;
    private String tokenType;

    private Long id;
    private String fullName;
    private String email;

    private Role role;
    private AccountStatus accountStatus;

    private String rollNumber;
    private String employeeId;

    private LocalDateTime timestamp;
}
