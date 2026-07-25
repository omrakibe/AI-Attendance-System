package in.attendai.attendanceservice.service;

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
    public InternalUserResponse getUserById(Long userId)
    {
        try
        {
            return authClient.getUserById(userId);
        } catch (Exception ex)
        {
            throw new UserNotFoundException("User not found with id: " + userId);
        }
    }

    @Override
    public InternalUserResponse validateFaculty(Long facultyId)
    {
        InternalUserResponse user = getUserById(facultyId);

        if (user.getStatus() != AccountStatus.ACTIVE)
        {
            throw new InactiveUserException("Faculty is not active with id: " + facultyId);
        }

        if (user.getRole() != Role.FACULTY)
        {
            throw new InvalidFacultyException("User is not a faculty with id: " + facultyId);
        }

        return user;
    }

    @Override
    public InternalUserResponse validateStudent(Long studentId)
    {
        InternalUserResponse user = getUserById(studentId);

        if (user.getStatus() != AccountStatus.ACTIVE)
        {
            throw new InactiveUserException("Student is not active with id: " + studentId);
        }

        if (user.getRole() != Role.STUDENT)
        {
            throw new UserNotFoundException("User is not a student with id: " + studentId);
        }

        return user;
    }
}
