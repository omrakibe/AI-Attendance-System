package in.attendai.attendanceservice.service;

import in.attendai.attendanceservice.dto.response.InternalUserResponse;

public interface IUserValidationService
{
    InternalUserResponse getUserById(Long userId);

    InternalUserResponse validateFaculty(Long facultyId);

    InternalUserResponse validateStudent(Long studentId);
}
