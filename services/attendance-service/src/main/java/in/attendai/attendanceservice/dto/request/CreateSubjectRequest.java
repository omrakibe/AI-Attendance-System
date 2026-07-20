package in.attendai.attendanceservice.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
public class CreateSubjectRequest {

    @NotBlank(message = "Subject code is required.")
    private String subjectCode;

    @NotBlank(message = "Subject name is required.")
    private String subjectName;

    @NotBlank(message = "Department is required.")
    private String department;

    @NotNull(message = "Semester is required.")
    @Min(1)
    @Max(8)
    private Integer semester;

    @NotNull(message = "Faculty ID is required.")
    private Long facultyId;
}
