package in.attendai.auth.exception;

public class AccountAlreadyProcessedException extends RuntimeException
{
    public AccountAlreadyProcessedException(String message)
    {
        super(message);
    }
}
