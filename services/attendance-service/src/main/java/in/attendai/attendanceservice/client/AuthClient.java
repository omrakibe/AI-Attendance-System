package in.attendai.attendanceservice.client;

import in.attendai.attendanceservice.dto.response.InternalUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "auth-service",
        path = "/internal/users"
)
public interface AuthClient
{

    @GetMapping("/{id}")
    InternalUserResponse getUserById(@PathVariable Long id);

}
