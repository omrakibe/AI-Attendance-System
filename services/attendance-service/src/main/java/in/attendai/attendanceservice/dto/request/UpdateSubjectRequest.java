package in.attendai.attendanceservice.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
public class UpdateSubjectRequest
{

    @NotBlank
    private String subjectName;

    @NotBlank
    private String department;

    @NotNull
    @Min(1)
    @Max(8)
    private Integer semester;

    @NotNull
    private String facultyId;

    @NotNull
    private Boolean active;
}
