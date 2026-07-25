package in.attendai.attendanceservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class SubjectResponse {

    private Long id;

    private String subjectCode;

    private String subjectName;

    private String department;

    private Integer semester;

    private Long facultyId;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
