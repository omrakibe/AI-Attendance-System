package in.attendai.auth.exception;

public class PendingRegistrationExistsException extends RuntimeException
{
    public PendingRegistrationExistsException(String message)
    {
        super(message);
    }
}
