package in.attendai.auth.exception;

public class PendingRegistrationNotFoundException extends RuntimeException
{
    public PendingRegistrationNotFoundException(String s)
    {
        super(s);
    }
}
