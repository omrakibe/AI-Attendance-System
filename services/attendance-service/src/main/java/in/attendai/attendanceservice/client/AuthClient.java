package in.attendai.attendanceservice.client;

import in.attendai.attendanceservice.dto.response.InternalUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "AUTH-SERVICE",
        path = "/api/auth/internal"
)
public interface AuthClient
{

    @GetMapping("/employee/{employeeId}")
    InternalUserResponse getUserByEmployeeId(
            @PathVariable("employeeId") String employeeId
    );

    @GetMapping("/student/{rollNumber}")
    InternalUserResponse getUserByRollNumber(
            @PathVariable String rollNumber
    );

}
