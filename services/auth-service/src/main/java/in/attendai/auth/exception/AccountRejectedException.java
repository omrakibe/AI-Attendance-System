package in.attendai.auth.exception;

public class AccountRejectedException extends RuntimeException
{
    public AccountRejectedException(String message)
    {
        super(message);
    }
}
