package in.attendai.attendanceservice.service;

import in.attendai.attendanceservice.dto.UpdateSubjectStatusRequest;
import in.attendai.attendanceservice.dto.request.CreateSubjectRequest;
import in.attendai.attendanceservice.dto.request.UpdateSubjectRequest;
import in.attendai.attendanceservice.dto.response.InternalUserResponse;
import in.attendai.attendanceservice.dto.response.SubjectResponse;
import in.attendai.attendanceservice.entity.Subject;
import in.attendai.attendanceservice.exception.SubjectAlreadyExistsException;
import in.attendai.attendanceservice.exception.SubjectNotFoundException;
import in.attendai.attendanceservice.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SubjectService implements ISubjectService
{

    private final SubjectRepository subjectRepository;
    private final IUserValidationService userValidationService;

    @Override
    public SubjectResponse createSubject(CreateSubjectRequest request)
    {
        if (subjectRepository.existsBySubjectCode(request.getSubjectCode()))
        {
            throw new SubjectAlreadyExistsException(
                    "Subject already exists with code: " + request.getSubjectCode()
            );
        }

        InternalUserResponse faculty = userValidationService.validateFaculty(request.getFacultyId());

        Subject subject = Subject.builder()
                .subjectCode(request.getSubjectCode())
                .subjectName(request.getSubjectName())
                .department(request.getDepartment())
                .semester(request.getSemester())
                .facultyId(faculty.getId())
                .active(true)
                .build();

        Subject savedSubject = subjectRepository.save(subject);
        return mapToResponse(savedSubject);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubjectResponse> getAllSubjects()
    {
        return subjectRepository.findByActiveTrue()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SubjectResponse getSubjectById(Long subjectId)
    {
        Subject subject = getSubjectEntityById(subjectId);
        return mapToResponse(subject);
    }

    @Override
    public SubjectResponse updateSubject(Long subjectId, UpdateSubjectRequest request)
    {
        Subject subject = getSubjectEntityById(subjectId);

        InternalUserResponse faculty = userValidationService.validateFaculty(request.getFacultyId());

        subject.setSubjectName(request.getSubjectName());
        subject.setDepartment(request.getDepartment());
        subject.setSemester(request.getSemester());
        subject.setFacultyId(faculty.getId());
        subject.setActive(request.getActive());

        Subject updatedSubject = subjectRepository.save(subject);
        return mapToResponse(updatedSubject);
    }

    @Override
    public SubjectResponse updateSubjectStatus(Long subjectId, UpdateSubjectStatusRequest request)
    {
        Subject subject = getSubjectEntityById(subjectId);

        subject.setActive(request.getActive());

        Subject updatedSubject = subjectRepository.save(subject);
        return mapToResponse(updatedSubject);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubjectResponse> getSubjectsByFaculty(Long facultyId)
    {
        return subjectRepository.findByFacultyIdAndActiveTrue(facultyId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private Subject getSubjectEntityById(Long subjectId)
    {
        return subjectRepository.findById(subjectId)
                .orElseThrow(() ->
                        new SubjectNotFoundException("Subject not found with id: " + subjectId)
                );
    }

    private SubjectResponse mapToResponse(Subject subject)
    {
        return SubjectResponse.builder()
                .id(subject.getId())
                .subjectCode(subject.getSubjectCode())
                .subjectName(subject.getSubjectName())
                .department(subject.getDepartment())
                .semester(subject.getSemester())
                .facultyId(subject.getFacultyId())
                .active(subject.getActive())
                .createdAt(subject.getCreatedAt())
                .updatedAt(subject.getUpdatedAt())
                .build();
    }
}
