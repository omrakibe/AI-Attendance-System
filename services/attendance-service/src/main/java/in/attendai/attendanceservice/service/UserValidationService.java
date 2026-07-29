package in.attendai.attendanceservice.service;

import feign.FeignException;
import in.attendai.attendanceservice.client.AuthClient;
import in.attendai.attendanceservice.dto.response.InternalUserResponse;
import in.attendai.attendanceservice.enums.AccountStatus;
import in.attendai.attendanceservice.enums.Role;
import in.attendai.attendanceservice.exception.InactiveUserException;
import in.attendai.attendanceservice.exception.InvalidFacultyException;
import in.attendai.attendanceservice.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserValidationService implements IUserValidationService
{

    private final AuthClient authClient;

    @Override
    public InternalUserResponse validateFaculty(String employeeId)
    {

        InternalUserResponse user;

        try
        {
            user = authClient.getUserByEmployeeId(employeeId);
        } catch (FeignException.NotFound ex)
        {
            throw new UserNotFoundException(
                    "Faculty not found. Employee ID: " + employeeId
            );
        }

        if (user.getStatus() != AccountStatus.ACTIVE)
        {
            throw new InactiveUserException(
                    "Faculty is not active. Employee ID: " + employeeId
            );
        }

        if (user.getRole() != Role.FACULTY)
        {
            throw new InvalidFacultyException(
                    "User is not a faculty. Employee ID: " + employeeId
            );
        }

        return user;
    }

    @Override
    public InternalUserResponse validateStudent(String rollNumber)
    {

        InternalUserResponse user;

        try
        {
            user = authClient.getUserByRollNumber(rollNumber);
        } catch (FeignException.NotFound ex)
        {
            throw new UserNotFoundException(
                    "Student not found. Roll Number: " + rollNumber
            );
        }

        if (user.getStatus() != AccountStatus.ACTIVE)
        {
            throw new InactiveUserException(
                    "Student is not active. Roll Number: " + rollNumber
            );
        }

        if (user.getRole() != Role.STUDENT)
        {
            throw new UserNotFoundException(
                    "User is not a student. Roll Number: " + rollNumber
            );
        }

        return user;
    }
}