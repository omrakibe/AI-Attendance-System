package in.attendai.auth.exception;

public class EmployeeIdAlreadyExistsException extends RuntimeException
{
    public EmployeeIdAlreadyExistsException(String message)
    {
        super(message);
    }
}
