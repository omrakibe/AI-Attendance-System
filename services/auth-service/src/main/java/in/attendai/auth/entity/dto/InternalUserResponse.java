package in.attendai.auth.entity.dto;

import in.attendai.auth.enums.Role;
import in.attendai.auth.enums.AccountStatus;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InternalUserResponse {

    private Long id;

    private String fullName;

    private String email;

    private Role role;

    private AccountStatus status;

    private String rollNumber;

    private String employeeId;
}
