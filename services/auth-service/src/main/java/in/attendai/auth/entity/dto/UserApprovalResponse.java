package in.attendai.auth.entity.dto;

import in.attendai.auth.enums.AccountStatus;
import in.attendai.auth.enums.Role;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserApprovalResponse
{

    private Long id;
    private String fullName;
    private String email;
    private Role role;
    private String employeeId;
    private String rollNumber;
    private AccountStatus status;
    private LocalDateTime createdAt;
}
