package in.attendai.attendanceservice.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateSubjectStatusRequest
{
    @NotNull
    private Boolean active;
}
