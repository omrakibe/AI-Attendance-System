package in.attendai.auth.exception;

public class InvalidOtpException extends RuntimeException
{
    public InvalidOtpException(String s)
    {
        super(s);
    }
}
