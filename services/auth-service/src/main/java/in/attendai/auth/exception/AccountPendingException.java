package in.attendai.auth.exception;

public class AccountPendingException extends RuntimeException
{
    public AccountPendingException(String message)
    {
        super(message);
    }
}
