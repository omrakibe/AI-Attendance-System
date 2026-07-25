package in.attendai.attendanceservice.dto.response;

import in.attendai.attendanceservice.enums.AccountStatus;
import in.attendai.attendanceservice.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InternalUserResponse
{

    private Long id;

    private String fullName;

    private String email;

    private Role role;

    private AccountStatus status;

    private String employeeId;

    private String rollNumber;

}
