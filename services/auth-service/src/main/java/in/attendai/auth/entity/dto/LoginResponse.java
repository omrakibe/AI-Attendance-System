package in.attendai.auth.entity.dto;

import in.attendai.auth.enums.AccountStatus;
import in.attendai.auth.enums.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse
{

    private String token;

    private String fullName;

    private String type;

    private String email;

    private Role role;

    private AccountStatus status;
}
