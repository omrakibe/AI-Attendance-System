package in.attendai.attendanceservice.service;

import in.attendai.attendanceservice.dto.UpdateSubjectStatusRequest;
import in.attendai.attendanceservice.dto.request.CreateSubjectRequest;
import in.attendai.attendanceservice.dto.request.UpdateSubjectRequest;
import in.attendai.attendanceservice.dto.response.SubjectResponse;

import java.util.List;

public interface ISubjectService
{
    SubjectResponse createSubject(CreateSubjectRequest request);

    List<SubjectResponse> getAllSubjects();

    SubjectResponse getSubjectById(Long subjectId);

    SubjectResponse updateSubject(Long subjectId, UpdateSubjectRequest request);

    SubjectResponse updateSubjectStatus(Long subjectId, UpdateSubjectStatusRequest request);

    List<SubjectResponse> getSubjectsByFaculty(Long facultyId);
}
