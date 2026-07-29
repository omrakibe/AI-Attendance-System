package in.attendai.attendanceservice.controller;

import in.attendai.attendanceservice.dto.UpdateSubjectStatusRequest;
import in.attendai.attendanceservice.dto.request.CreateSubjectRequest;
import in.attendai.attendanceservice.dto.request.UpdateSubjectRequest;
import in.attendai.attendanceservice.dto.response.SubjectResponse;
import in.attendai.attendanceservice.service.ISubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance/subjects")
@RequiredArgsConstructor
public class SubjectController
{

    private final ISubjectService subjectService;

    @PostMapping
    public SubjectResponse createSubject(@Valid @RequestBody CreateSubjectRequest request)
    {
        return subjectService.createSubject(request);
    }

    @GetMapping
    public List<SubjectResponse> getAllSubjects()
    {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/{id}")
    public SubjectResponse getSubjectById(@PathVariable("id") Long subjectId)
    {
        return subjectService.getSubjectById(subjectId);
    }

    @PutMapping("/{id}")
    public SubjectResponse updateSubject(
            @PathVariable("id") Long subjectId,
            @Valid @RequestBody UpdateSubjectRequest request
    )
    {
        return subjectService.updateSubject(subjectId, request);
    }

    @PatchMapping("/{id}/status")
    public SubjectResponse updateSubjectStatus(
            @PathVariable("id") Long subjectId,
            @Valid @RequestBody UpdateSubjectStatusRequest request
    )
    {
        return subjectService.updateSubjectStatus(subjectId, request);
    }

    @GetMapping("/faculty/{facultyId}")
    public List<SubjectResponse> getSubjectsByFaculty(@PathVariable Long facultyId)
    {
        return subjectService.getSubjectsByFaculty(facultyId);
    }
}
