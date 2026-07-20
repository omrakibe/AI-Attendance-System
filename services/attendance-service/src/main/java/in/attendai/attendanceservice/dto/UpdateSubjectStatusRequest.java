package in.attendai.attendanceservice.dto;


import jakarta.validation.constraints.NotNull;

public class UpdateSubjectStatusRequest
{
    @NotNull
    private Boolean active;
}
