package in.attendai.attendanceservice.controller;

import in.attendai.attendanceservice.dto.UpdateSubjectStatusRequest;
import in.attendai.attendanceservice.dto.request.CreateSubjectRequest;
import in.attendai.attendanceservice.dto.request.UpdateSubjectRequest;
import in.attendai.attendanceservice.dto.response.SubjectResponse;
import in.attendai.attendanceservice.service.ISubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance/subjects")
@RequiredArgsConstructor
public class SubjectController
{

    private final ISubjectService subjectService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public SubjectResponse createSubject(@Valid @RequestBody CreateSubjectRequest request)
    {
        return subjectService.createSubject(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<SubjectResponse> getAllSubjects()
    {
        return subjectService.getAllSubjects();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('FACULTY')")
    @GetMapping("/{id}")
    public SubjectResponse getSubjectById(@PathVariable("id") Long subjectId)
    {
        return subjectService.getSubjectById(subjectId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public SubjectResponse updateSubject(
            @PathVariable("id") Long subjectId,
            @Valid @RequestBody UpdateSubjectRequest request
    )
    {
        return subjectService.updateSubject(subjectId, request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/status")
    public SubjectResponse updateSubjectStatus(
            @PathVariable("id") Long subjectId,
            @Valid @RequestBody UpdateSubjectStatusRequest request
    )
    {
        return subjectService.updateSubjectStatus(subjectId, request);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('FACULTY')")
    @GetMapping("/faculty/{facultyId}")
    public List<SubjectResponse> getSubjectsByFaculty(@PathVariable Long facultyId)
    {
        return subjectService.getSubjectsByFaculty(facultyId);
    }
}
