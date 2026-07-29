package in.attendai.attendanceservice.service;

import in.attendai.attendanceservice.dto.response.InternalUserResponse;

public interface IUserValidationService
{

    InternalUserResponse validateFaculty(String facultyId);

    InternalUserResponse validateStudent(String studentId);
}
