package in.attendai.attendanceservice.exception;

public class SubjectAlreadyExistsException extends RuntimeException
{
    public SubjectAlreadyExistsException(String message)
    {
        super(message);
    }
}
